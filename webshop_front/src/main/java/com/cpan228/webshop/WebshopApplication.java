package com.cpan228.webshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cpan228.webshop.model.Item;
import com.cpan228.webshop.model.Item.Brand;
import com.cpan228.webshop.repository.ItemRepository;

/**
 * mvn spring-boot:run does following steps
 * 1. mvn clean
 * 2. mvn compile
 * 3. mvn package
 * 4. java -jar target/tekkenreborn-0.0.1-SNAPSHOT.jar
 * 5. deploys jar to embedded tomcat
 */
@SpringBootApplication
public class WebshopApplication {

	/**
	 * This is the main method that starts the application
	 * Spring Application Context is created here
	 * You can configure your application properties using @param args
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ItemRepository repository) {
		return args -> {
			repository.save(Item.builder()
					.name("Tie Dye Shirt")
					.price(2099.99)
					.yearOfCreation(2023)
					.brandFrom(Brand.GUCCI)
					.quantity(4)
					.build());

			repository.save(Item.builder()
					.name("Grey Polo")
					.price(1199.99)
					.yearOfCreation(2025)
					.brandFrom(Brand.Polo)
					.quantity(11)
					.build());

			repository.save(Item.builder()
					.name("Yellow Rain Coat")
					.price(4999.99)
					.yearOfCreation(2023)
					.brandFrom(Brand.Awake_NY)
					.quantity(20)
					.build());

			repository.save(Item.builder()
					.name("Black Cardigan")
					.price(2000.99)
					.yearOfCreation(2023)
					.brandFrom(Brand.CDG)
					.quantity(5)
					.build());

			repository.save(Item.builder()
					.name("Purple Hoodie")
					.price(1177.99)
					.yearOfCreation(2028)
					.brandFrom(Brand.YSL)
					.quantity(30)
					.build());

			repository.save(Item.builder()
					.name("Black Blazer")
					.price(4999.99)
					.yearOfCreation(2023)
					.brandFrom(Brand.DIOR)
					.quantity(23)
					.build());

			repository.save(Item.builder()
					.name("Umbrella")
					.price(11999.99)
					.yearOfCreation(2027)
					.brandFrom(Brand.Louis_Vuitton)
					.quantity(9)
					.build());

		};
	}

}