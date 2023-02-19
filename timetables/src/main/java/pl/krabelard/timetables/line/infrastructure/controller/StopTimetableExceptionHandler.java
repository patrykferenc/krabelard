package pl.krabelard.timetables.line.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.krabelard.timetables.line.domain.exception.LineTimetableFetchingException;

@ControllerAdvice
public class StopTimetableExceptionHandler {

	@ExceptionHandler(LineTimetableFetchingException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<String> ineTimetableFetchingExceptionHandler(
		LineTimetableFetchingException ex
	) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
