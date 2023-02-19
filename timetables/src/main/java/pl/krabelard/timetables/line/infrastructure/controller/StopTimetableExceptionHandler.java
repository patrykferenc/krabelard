package pl.krabelard.timetables.line.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.krabelard.timetables.line.domain.exception.LineTimetableFetchingException;

@ControllerAdvice
public class StopTimetableExceptionHandler {

	@ExceptionHandler(LineTimetableFetchingException.class)
	public ResponseEntity<String> ineTimetableFetchingExceptionHandler(
		LineTimetableFetchingException ex
	) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
