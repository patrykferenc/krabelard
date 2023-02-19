package pl.krabelard.timetables.line.infrastructure.repository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.krabelard.timetables.line.domain.entity.BusStop;
import pl.krabelard.timetables.line.domain.entity.LineTimetable;
import pl.krabelard.timetables.line.domain.exception.LineTimetableFetchingException;
import pl.krabelard.timetables.line.domain.usecase.ZtmLineTimetableRepository;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableApiResource;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapper;

@Service
public class ZtmLineTimetableRepositoryImpl
	implements ZtmLineTimetableRepository {

	@Value("${ztm.api.key}")
	private String ZTM_API_KEY;

	@Value("${ztm.query.timetable.id}")
	private String ZTM_QUERY_TIMETABLE_ID;

	@Value("${ztm.query.string}")
	private String ZTM_QUERY_STRING;

	@Value("${ztm.query.param.string}")
	private String ZTM_QUERY_PARAM_STRING;

	private final RestTemplate restTemplate;
	private final ZtmLineTimetableMapper mapper;

	@Autowired
	public ZtmLineTimetableRepositoryImpl(
		RestTemplateBuilder restTemplateBuilder,
		ZtmLineTimetableMapper mapper
	) {
		this.restTemplate = restTemplateBuilder.messageConverters().build();
		this.mapper = mapper;
	}

	ZtmLineTimetableRepositoryImpl(
		RestTemplate restTemplate,
		ZtmLineTimetableMapper mapper,
		String timetableId,
		String queryString,
		String paramString,
		String apiKey
	) {
		this.restTemplate = restTemplate;
		this.mapper = mapper;
		this.ZTM_API_KEY = apiKey;
		this.ZTM_QUERY_TIMETABLE_ID = timetableId;
		this.ZTM_QUERY_STRING = queryString;
		this.ZTM_QUERY_PARAM_STRING = paramString;
	}

	@Override
	public LineTimetable getFor(BusStop stop) {
		var ztmRawResponse = Optional
			.ofNullable(
				this.restTemplate.getForObject(
						queryFor(stop),
						ZtmLineTimetableApiResource.class
					)
			)
			.orElseThrow(() ->
				new LineTimetableFetchingException(
					"Error fetching data from ZTM repository"
				)
			);

		var timetable = mapper.map(ztmRawResponse);
		if (timetable.isEmpty()) {
			throw new LineTimetableFetchingException(
				"There are no timetables characterised by given query parameters"
			);
		}
		return new LineTimetable(timetable);
	}

	private String queryFor(BusStop stop) {
		return String.format(
			ZTM_QUERY_PARAM_STRING,
			ZTM_QUERY_STRING,
			ZTM_QUERY_TIMETABLE_ID,
			stop.name(),
			stop.number(),
			stop.line(),
			ZTM_API_KEY
		);
	}
}
