package coffeehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("coffeehouse.repo")
@EntityScan("coffeehouse.entity")
public class CoffeeHouseApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoffeeHouseApplication.class, args);
	}
}
