package pl.krabelard.timetables.line.infrastructure.repository;

import java.util.Optional;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import pl.krabelard.timetables.line.domain.entity.BusStop;
import pl.krabelard.timetables.line.domain.entity.LineTimetable;
import pl.krabelard.timetables.line.domain.exception.LineTimetableFetchingException;
import pl.krabelard.timetables.line.domain.usecase.ZtmLineTimetableRepository;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableApiResource;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapper;

public class ZtmLineTimetableRepositoryImpl
	implements ZtmLineTimetableRepository {

	private final String apiKey;

	private final String resourceId;

	private final String baseUri;

	private final String paramUri;

	private final RestTemplate restTemplate;
	private final ZtmLineTimetableMapper mapper;

	public ZtmLineTimetableRepositoryImpl(
		String apiKey,
		String resourceId,
		String baseUri,
		String paramUri,
		RestTemplateBuilder restTemplateBuilder,
		ZtmLineTimetableMapper mapper
	) {
		this.apiKey = apiKey;
		this.resourceId = resourceId;
		this.baseUri = baseUri;
		this.paramUri = paramUri;
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
		this.apiKey = apiKey;
		this.resourceId = timetableId;
		this.baseUri = queryString;
		this.paramUri = paramString;
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
			baseUri,
			paramUri,
			resourceId,
			stop.name(),
			stop.number(),
			stop.line(),
			apiKey
		);
	}
}
