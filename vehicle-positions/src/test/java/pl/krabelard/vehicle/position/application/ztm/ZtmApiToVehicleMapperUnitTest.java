package pl.krabelard.vehicle.position.application.ztm;

import org.junit.jupiter.api.Test;
import pl.krabelard.vehicle.position.domain.model.value.Position;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;
import pl.krabelard.vehicle.position.test.TimeUtils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ZtmApiToVehicleMapperUnitTest {

	@Test
	void shouldMapToVehicleWithCorrectValues_fromValidZtmVehiclePositionDTO() {
		// given a valid ZtmVehiclePositionDTO
		final var validZtmVehiclePositionDTO = new ZtmVehiclePositionDTO(
			"1",
			11,
			12.0,
			3.0,
			TimeUtils.LOCAL_DATE_TIME_NOW_MOCKED
		);

		// when
		final var vehicleMappedFromDTO = ZtmApiToVehicleMapper.mapToVehicle(
			validZtmVehiclePositionDTO,
			VehicleType.BUS
		);

		// then
		assertAll(
			() -> assertEquals(11, vehicleMappedFromDTO.getVehicleNumber()),
			() ->
				assertEquals(Position.of(12.0, 3.0), vehicleMappedFromDTO.getPosition()),
			() ->
				assertEquals(VehicleType.BUS, vehicleMappedFromDTO.getVehicleType()),
			() ->
				assertEquals(
					TimeUtils.LOCAL_DATE_TIME_NOW_MOCKED,
					vehicleMappedFromDTO.getTimestamp()
				)
		);
	}
}