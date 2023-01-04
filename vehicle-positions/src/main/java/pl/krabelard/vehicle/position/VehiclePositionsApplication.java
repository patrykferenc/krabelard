package pl.krabelard.vehicle.position;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class VehiclePositionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehiclePositionsApplication.class, args);
	}
}
