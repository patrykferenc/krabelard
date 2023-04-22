package pl.krabelard.vehicle.position.application.ztm.online;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.krabelard.vehicle.position.application.ztm.online.exception.ZtmWebApiClientIsUnauthorisedException;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;
import pl.krabelard.vehicle.position.test.IntegrationTest;

class ZtmWebApiClientIntegrationTest extends IntegrationTest {

	@Autowired
	private ZtmWebApiClientConfiguration ztmWebApiClientConfiguration;

	@Autowired
	private ZtmWebApiClientFactory ztmWebApiClientFactory;

	private MockedZtmApi mockedZtmApi;

	private ZtmWebApiClient ztmWebApiClient;

	@BeforeEach
	void setUp() {
		startMockedZtmApi();
		configureClient();
	}

	private void startMockedZtmApi() {
		mockedZtmApi = new MockedZtmApi(ztmWebApiClientConfiguration);
		mockedZtmApi.start();
	}

	private void configureClient() {
		ztmWebApiClient = ztmWebApiClientFactory.getClient();
	}

	@AfterEach
	void tearDown() {
		mockedZtmApi.stop();
	}

	@Nested
	@Disabled("TODO: Rewrite when rewriting to openfeign.")
	@DisplayName("When error tests")
	class ErrorHandlingTests {

		@Test
		void shouldThrowUnauthorisedException_whenApiKeyIsInvalid() {
			mockedZtmApi.thatReturnsUnauthorisedErrorWhenApiKeyIsInvalid();

			Assertions
				.assertThatThrownBy(() ->
					ztmWebApiClient.getAllVehiclePositionsForVehicleType(VehicleType.BUS)
				)
				.isInstanceOf(ZtmWebApiClientIsUnauthorisedException.class)
				.hasMessage("ZTM Web API Client is unauthorised because of invalid API key");
		}

		@Test
		void shouldThrowUnauthorisedException_whenNoApiKeyWasProvided() {
			mockedZtmApi.thatReturnsUnauthorisedErrorWhenApiKeyIsNotProvided();

			Assertions
				.assertThatThrownBy(() ->
					ztmWebApiClient.getAllVehiclePositionsForVehicleType(VehicleType.BUS)
				)
				.isInstanceOf(ZtmWebApiClientIsUnauthorisedException.class)
				.hasMessage("ZTM Web API Client is unauthorised because API key was not provided");
		}
		// TODO #KRB-86: Add tests for:
		//  - ZtmWebApiClientResourceDoesNotExistException (check parsing error on a 200 response)
		//  - [someException] when there is a 404-like response
		//  - [someException] when there is a timeout
		//  - [someException] when the status code is 404
		//  - [someException] when the status code is 5xx
	}

	@Nested
	@DisplayName("Getting positions of given line and vehicle type tests")
	class GetVehiclePositionsForLineAndVehicleTypeTests {

		@Test
		void shouldReturnListOfVehiclePositionsForGivenLineAndVehicleType_whenRequestingListOfBuses() {
			// given that the ZTM api will return an expected list of vehicle positions
			mockedZtmApi.thatReturnsListOfBusPositionsOfOnlyOneLine();

			// when
			final var vehiclePositions = ztmWebApiClient.getVehiclePositionsForLineAndVehicleType(
				Line.of("179"),
				VehicleType.BUS
			);

			final var expectedVehiclePositions = MockedZtmApi.getExpectedBusPositionsForMockedResponseOfOnlyOneLine();
			// then
			Assertions
				.assertThat(vehiclePositions)
				.isNotEmpty()
				.hasSize(expectedVehiclePositions.size())
				.containsExactlyInAnyOrderElementsOf(expectedVehiclePositions);
		}

		@Test
		void shouldReturnListOfVehiclePositionsForGivenLineAndVehicleType_whenRequestingListOfTrams() {
			// given that the ZTM api will return an expected list of vehicle positions
			mockedZtmApi.thatReturnsListOfTramPositionsOfOnlyOneLine();

			// when
			final var vehiclePositions = ztmWebApiClient.getVehiclePositionsForLineAndVehicleType(
				Line.of("17"),
				VehicleType.TRAM
			);

			final var expectedVehiclePositions = MockedZtmApi.getExpectedTramPositionsForMockedResponseOfOnlyOneLine();
			// then
			Assertions
				.assertThat(vehiclePositions)
				.isNotEmpty()
				.hasSize(expectedVehiclePositions.size())
				.containsExactlyInAnyOrderElementsOf(expectedVehiclePositions);
		}
	}

	@Nested
	@DisplayName("Getting positions of all lines of given vehicle type tests")
	class GetVehiclePositionsForVehicleTypeTests {

		@Test
		void shouldReturnEmptyList_whenReturnedPositionsWereEmpty() {
			// given that api with line is available
			mockedZtmApi.thatReturnsEmptyListWithQueryParameterOfBusesOrTrams(true);

			// when requesting positions for line
			final var returnedPositionDTOs = ztmWebApiClient.getAllVehiclePositionsForVehicleType(
				VehicleType.TRAM
			);

			Assertions.assertThat(returnedPositionDTOs).isEmpty();
		}

		@Test
		void shouldReturnListOfDifferentBusPositions_whenRequestingBuses() {
			// given that the ZTM api will return an expected list of vehicle positions of given type
			mockedZtmApi.thatReturnsListOfDifferentBusPositions();

			// when requesting positions for line
			final var returnedPositionDTOs = ztmWebApiClient.getAllVehiclePositionsForVehicleType(
				VehicleType.BUS
			);

			final var expectedVehiclePositions = MockedZtmApi.getExpectedBusPositionsForMockedResponseOfDifferentLines();
			Assertions
				.assertThat(returnedPositionDTOs)
				.isNotEmpty()
				.hasSize(expectedVehiclePositions.size())
				.containsExactlyInAnyOrderElementsOf(expectedVehiclePositions);
		}

		@Test
		void shouldReturnListOfDifferentTramPositions_whenRequestingTrams() {
			// given that the ZTM api will return an expected list of vehicle positions of given type
			mockedZtmApi.thatReturnsListOfDifferentTramPositions();

			// when requesting positions for line
			final var returnedPositionDTOs = ztmWebApiClient.getAllVehiclePositionsForVehicleType(
				VehicleType.TRAM
			);

			final var expectedVehiclePositions = MockedZtmApi.getExpectedTramPositionsForMockedResponseOfDifferentLines();
			Assertions
				.assertThat(returnedPositionDTOs)
				.isNotEmpty()
				.hasSize(expectedVehiclePositions.size())
				.containsExactlyInAnyOrderElementsOf(expectedVehiclePositions);
		}
	}
}
