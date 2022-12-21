package pl.krabelard.vehicle.position.application.ztm.online;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.vavr.Function1;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.krabelard.vehicle.position.application.ztm.mock.MockedZtmApi;
import pl.krabelard.vehicle.position.application.ztm.online.exception.ZtmWebApiClientIsUnauthorisedException;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;

class ZtmWebApiClientUnitTest {

	private WireMockServer wireMockServer;

	private ZtmWebApiClient ztmWebApiClient;

	@BeforeEach
	void setUp() {
		startMockedZtmApi();
		configureClient();
	}

	private void startMockedZtmApi() {
		wireMockServer = new WireMockServer();
		wireMockServer.start();
	}

	private void configureClient() {
		ZtmWebApiClientFactory ztmWebApiClientFactory = new ZtmWebApiClientTestFactory(
			wireMockServer.baseUrl(),
			MockedZtmApi.MOCKED_ZTM_API_POSITIONS_RESOURCE_URL,
			MockedZtmApi.POSITIONS_RESOURCE_ID,
			MockedZtmApi.MOCKED_VALID_API_KEY
		);

		ztmWebApiClient = ztmWebApiClientFactory.getClient();
	}

	@AfterEach
	void tearDown() {
		wireMockServer.stop();
	}

	@Nested
	@DisplayName("When error tests")
	class ErrorHandlingTests {

		@Test
		void shouldThrowUnauthorisedException_whenApiKeyIsInvalid() {
			MockedZtmApi.mockZtmApiThatReturnsUnauthorisedErrorWhenApiKeyIsInvalid(
				wireMockServer
			);

			Assertions
				.assertThatThrownBy(() ->
					ztmWebApiClient.getAllVehiclePositionsForVehicleType(VehicleType.BUS)
				)
				.isInstanceOf(ZtmWebApiClientIsUnauthorisedException.class)
				.hasMessage(
					"ZTM Web API Client is unauthorised because of invalid API key"
				);
		}

		@Test
		void shouldThrowUnauthorisedException_whenNoApiKeyWasProvided() {
			MockedZtmApi.mockZtmApiThatReturnsUnauthorisedErrorWhenApiKeyIsNotProvided(
				wireMockServer
			);

			Assertions
				.assertThatThrownBy(() ->
					ztmWebApiClient.getAllVehiclePositionsForVehicleType(VehicleType.BUS)
				)
				.isInstanceOf(ZtmWebApiClientIsUnauthorisedException.class)
				.hasMessage(
					"ZTM Web API Client is unauthorised because API key was not provided"
				);
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

		private static Stream<Arguments> getVehiclePositionsForLineAndVehicleTypeTestCases() {
			return Stream.of(
				Arguments.of(
					Function1.of(
						MockedZtmApi::mockZtmApiThatReturnsListOfBusPositionsFunction
					),
					Line.of("179"),
					VehicleType.BUS,
					MockedZtmApi.getExpectedBusPositionDTOsForMockedResponseOfOneLine()
				),
				Arguments.of(
					Function1.of(
						MockedZtmApi::mockZtmApiThatReturnsListOfTramPositionsFunction
					),
					Line.of("17"),
					VehicleType.TRAM,
					MockedZtmApi.getExpectedTramPositionDTOsForMockedResponseOfOneLine()
				)
			);
		}

		@ParameterizedTest
		@MethodSource("getVehiclePositionsForLineAndVehicleTypeTestCases")
		void shouldReturnListOfVehiclePositionsForGivenLineAndVehicleType_whenArgumentsAreCorrect(
			Function1<WireMockServer, Void> setupMockedZtmApi,
			Line line,
			VehicleType vehicleType,
			List<ZtmVehiclePositionDTO> expectedVehiclePositions
		) {
			// given that the ZTM api will return an expected list of vehicle positions
			setupMockedZtmApi.apply(wireMockServer);

			// when
			var vehiclePositions = ztmWebApiClient.getVehiclePositionsForLineAndVehicleType(
				line,
				vehicleType
			);

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
			MockedZtmApi.mockZtmApiThatReturnsEmptyListWithQueryParameter(
				wireMockServer,
				true
			);

			// when requesting positions for line
			final var returnedPositionDTOs = ztmWebApiClient.getAllVehiclePositionsForVehicleType(
				VehicleType.TRAM
			);

			Assertions.assertThat(returnedPositionDTOs).isEmpty();
		}

		private static Stream<Arguments> getVehiclePositionsForVehicleTypeTestCases() {
			return Stream.of(
				Arguments.of(
					Function1.of(
						MockedZtmApi::mockZtmApiThatReturnsListOfDifferentBusPositionsFunction
					),
					VehicleType.BUS,
					MockedZtmApi.getExpectedBusPositionDTOsForMockedResponseOfDifferentLines()
				),
				Arguments.of(
					Function1.of(
						MockedZtmApi::mockZtmApiThatReturnsListOfDifferentTramPositionsFunction
					),
					VehicleType.TRAM,
					MockedZtmApi.getExpectedTramPositionDTOsForMockedResponseOfDifferentLines()
				)
			);
		}

		@ParameterizedTest
		@MethodSource("getVehiclePositionsForVehicleTypeTestCases")
		void shouldReturnListOfBusAndTramPositions_whenArgumentsAreCorrect(
			Function1<WireMockServer, Void> setupMockedZtmApi,
			VehicleType vehicleType,
			List<ZtmVehiclePositionDTO> expectedVehiclePositions
		) {
			// given that the ZTM api will return an expected list of vehicle positions of given type
			setupMockedZtmApi.apply(wireMockServer);

			// when requesting positions for line
			final var returnedPositionDTOs = ztmWebApiClient.getAllVehiclePositionsForVehicleType(
				vehicleType
			);

			Assertions
				.assertThat(returnedPositionDTOs)
				.isNotEmpty()
				.hasSize(expectedVehiclePositions.size())
				.containsExactlyInAnyOrderElementsOf(expectedVehiclePositions);
		}
	}
}
