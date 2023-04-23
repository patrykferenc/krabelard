package pl.krabelard.timetables.line.infrastructure.repository;

import java.net.URI;
import java.util.function.Function;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.util.UriBuilder;
import pl.krabelard.timetables.line.domain.entity.BusStop;
import pl.krabelard.timetables.line.domain.entity.LineTimetable;
import pl.krabelard.timetables.line.domain.exception.LineTimetableFetchingException;
import pl.krabelard.timetables.line.domain.usecase.ZtmLineTimetableRepository;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableApiResource;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapper;

public class ZtmLineTimetableRepositoryImpl implements ZtmLineTimetableRepository {

	private final String apiKey;

	private final String resourceId;

	private final ZtmLineTimetableMapper mapper;
	private final WebClient client;

	public ZtmLineTimetableRepositoryImpl(
		String apiKey,
		String resourceId,
		WebClient client,
		ZtmLineTimetableMapper mapper
	) {
		this.apiKey = apiKey;
		this.resourceId = resourceId;
		this.mapper = mapper;
		this.client = client;
	}

	@Override
	public LineTimetable getFor(BusStop stop) {
		var ztmRawResponse = from(stop);
		var timetable = mapper.map(ztmRawResponse);
		if (timetable.isEmpty()) {
			throw new LineTimetableFetchingException(
				"There are no timetables characterised by given query parameters"
			);
		}
		return new LineTimetable(timetable);
	}

	ZtmLineTimetableApiResource from(BusStop stop) {
		try {
			return client
				.get()
				.uri(buildFrom(stop))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(ZtmLineTimetableApiResource.class)
				.block();
		} catch (WebClientException ex) {
			throw new LineTimetableFetchingException(
				"Error fetching data from ZTM Api. ZTM Api contract might have changed."
			);
		}
	}

	Function<UriBuilder, URI> buildFrom(BusStop stop) {
		return x ->
			x
				.path("/api/action")
				.pathSegment("dbtimetable_get")
				.queryParam("id", resourceId)
				.queryParam("busstopId", stop.name())
				.queryParam("busstopNr", String.format("%02d", stop.number()))
				.queryParam("line", stop.line())
				.queryParam("apikey", apiKey)
				.build();
	}
}
