package pl.krabelard.vehicle.position.application.ztm.online;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import pl.krabelard.vehicle.position.application.ztm.online.json.ZtmApiIdDeserialiser;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ZtmVehiclePositionDTO {

	@JsonProperty("Lines")
	String line;

	@JsonProperty("VehicleNumber")
	@JsonDeserialize(using = ZtmApiIdDeserialiser.class)
	int vehicleNumber;

	@JsonProperty("Lat")
	double latitude;

	@JsonProperty("Lon")
	double longitude;

	@JsonProperty("Time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime timeOfLastUpdate;
}
