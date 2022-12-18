package pl.krabelard.vehicle.position.application.ztm;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Value;

@Value
public class ZtmVehiclePositionDTO {

	@JsonProperty("Lines")
	String line;

	@JsonProperty("VehicleNumber")
	int vehicleNumber;

	@JsonProperty("Lat")
	double latitude;

	@JsonProperty("Lon")
	double longitude;

	@JsonProperty("Time")
	LocalDateTime lastUpdate;
}
