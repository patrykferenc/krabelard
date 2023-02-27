package pl.krabelard.timetables.line.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.krabelard.timetables.line.domain.exception.LineTimetableFetchingException;

@RestControllerAdvice
public class StopTimetableExceptionHandler {

	@ExceptionHandler(LineTimetableFetchingException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String LineTimetableFetchingExceptionHandler(
		LineTimetableFetchingException ex
	) {
		return ex.getMessage();
	}
}
