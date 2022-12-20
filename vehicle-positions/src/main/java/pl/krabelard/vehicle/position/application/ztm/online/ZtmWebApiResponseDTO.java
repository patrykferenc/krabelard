package pl.krabelard.vehicle.position.application.ztm.online;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
class ZtmWebApiResponseDTO {

	List<ZtmVehiclePositionDTO> result;
}
