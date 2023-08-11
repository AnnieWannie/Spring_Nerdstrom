package com.cpan228.webshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cpan228.webshop.model.Item;
import com.cpan228.webshop.model.Item.Brand;

import com.cpan228.webshop.model.Distribution;
import com.cpan228.webshop.repository.DistributionRepository;
import com.cpan228.webshop.repository.ItemRepository;

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
	public CommandLineRunner dataLoader(ItemRepository itemRepository, DistributionRepository dcRepository) {

			if ( dcRepository.count() > 0) {
				return null;
			}

		return args -> {
				var torDistro = dcRepository.save(Distribution.builder().name("Toronto Distribution").latitude(10.0).longitude(10.9).build());
				var parDistro = dcRepository.save(Distribution.builder().name("Paris Distribution").latitude(192.0).longitude(57.8).build());
				var lonDistro = dcRepository.save(Distribution.builder().name("London Distribution").latitude(138).longitude(49.4).build());
				var nyDistro = dcRepository.save(Distribution.builder().name("New York Distribution").latitude(17.0).longitude(27.9).build());
				var milDistro = dcRepository.save(Distribution.builder().name("Milan Distribution").latitude(210.7).longitude(230.9).build());
				var tokDistro = dcRepository.save(Distribution.builder().name("Tokyo Distribution").latitude(208.7).longitude(178.6).build());

			itemRepository
					.save(Item.builder()
							.name("Tiger Sweater")
							.price(2199)
							.brandFrom(Brand.GUCCI)
							.yearOfCreation(2024)
							.quantity(10)
							.distribution(torDistro).build());
			itemRepository
					.save(Item.builder()
							.name("Car Coat")
							.price(3800)
							.brandFrom(Brand.YSL)
							.yearOfCreation(2027)
							.quantity(7)
							.distribution(torDistro).build());
			itemRepository
					.save(Item.builder()
							.name("Wallet")
							.price(1109)
							.brandFrom(Brand.DIOR)
							.yearOfCreation(2025)
							.quantity(15)
							.distribution(torDistro).build());
			itemRepository
					.save(Item.builder()
							.name("Yellow Lens Sunglasses")
							.price(2000)
							.brandFrom(Brand.Awake_NY)
							.yearOfCreation(2029)
							.quantity(32)
							.distribution(nyDistro).build());
			itemRepository
					.save(Item.builder()
							.name("Wallet")
							.price(1109)
							.brandFrom(Brand.DIOR)
							.yearOfCreation(2025)
							.quantity(44)
							.distribution(nyDistro).build());
			itemRepository
					.save(Item.builder()
							.name("Iridescent Bag")
							.price(77777)
							.brandFrom(Brand.Louis_Vuitton)
							.yearOfCreation(2077)
							.quantity(1)
							.distribution(nyDistro).build());
			itemRepository
					.save(Item.builder()
							.name("Belt")
							.price(4009)
							.brandFrom(Brand.GUCCI)
							.yearOfCreation(2033)
							.quantity(2)
							.distribution(parDistro).build());
			itemRepository
					.save(Item.builder()
							.name("Polo")
							.price(1009)
							.brandFrom(Brand.Polo)
							.yearOfCreation(2025)
							.quantity(215)
							.distribution(parDistro).build());
			itemRepository
					.save(Item.builder()
							.name("Cardigan")
							.price(2700)
							.brandFrom(Brand.CDG)
							.yearOfCreation(2030)
							.quantity(9)
							.distribution(parDistro).build());
		};
	}

}