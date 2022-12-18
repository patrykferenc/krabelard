package pl.krabelard.vehicle.position.application.ztm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDateTime;

@Value
class ZtmVehiclePositionDTO {

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
