package pl.krabelard.krapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.krabelard.krapi.properties.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class KrapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KrapiApplication.class, args);
	}
}
