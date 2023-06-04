package pl.krabelard.lines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pl.krabelard.lines.repositories")
public class LinesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinesApplication.class, args);
	}
}
