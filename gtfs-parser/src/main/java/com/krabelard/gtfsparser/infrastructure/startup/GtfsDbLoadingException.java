package com.krabelard.gtfsparser.infrastructure.startup;

public class GtfsDbLoadingException extends RuntimeException {
	public GtfsDbLoadingException(String message) {
		super(message);
	}
}
