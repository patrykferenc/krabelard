package pl.krabelard.vehicle.position.application.ztm.online.exception;

public class ZtmWebApiClientIsUnauthorisedException extends RuntimeException {

	private ZtmWebApiClientIsUnauthorisedException(String message) {
		super(message);
	}

	public static ZtmWebApiClientIsUnauthorisedException becauseOfInvalidApiKey() {
		return new ZtmWebApiClientIsUnauthorisedException(
			"ZTM Web API Client is unauthorised because of invalid API key"
		);
	}

	public static ZtmWebApiClientIsUnauthorisedException becauseOfNoApiKey() {
		return new ZtmWebApiClientIsUnauthorisedException(
			"ZTM Web API Client is unauthorised because API key was not provided"
		);
	}
}
