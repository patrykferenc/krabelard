package pl.krabelard.timetables.line.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.krabelard.timetables.line.domain.entity.LineTimetable;
import pl.krabelard.timetables.line.domain.exception.LineTimetableFetchingException;
import pl.krabelard.timetables.line.domain.usecase.LineTimetableService;
import pl.krabelard.timetables.line.domain.usecase.ZtmLineTimetableRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class StopTimetableControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LineTimetableService service;

	@MockBean
	private ZtmLineTimetableRepository repository;

	@Test
	void whenQueryCorrect_shouldReturnCorrectResponse() throws Exception {
		//given
		var responseExpected = Files
			.readString(
				Path.of("src/test/resources/line/repository-timetable-response.json")
			)
			.replaceAll("\\s+","");
		var serviceExpected = new LineTimetable(List.of(LocalTime.NOON));
		when(service.getFor(any())).thenReturn(serviceExpected);
		//when - then
		this.mockMvc.perform(get("/timetable/7009/1/520"))
			.andDo(print())
			.andExpect(content().string(responseExpected))
			.andExpect(status().isOk());
	}

	@Test
	void whenQueryIncorrect_shouldReturnCorrectResponse() throws Exception {
		//given
		when(service.getFor(any()))
			.thenThrow(
				new LineTimetableFetchingException(
					"Error fetching data from ZTM repository"
				)
			);
		//when - then
		this.mockMvc.perform(get("/timetable/7009/1/520"))
			.andDo(print())
			.andExpect(content().string("Error fetching data from ZTM repository"))
			.andExpect(status().isNotFound());
	}
}
