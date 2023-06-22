package org.medmota.demo;

import org.medmota.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstDemoSpringbootOracleDbApplication implements CommandLineRunner {

	@Autowired
	private CarService carService;
	
	public static void main(String[] args) {
		SpringApplication.run(FirstDemoSpringbootOracleDbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var counter = carService.getTotalCarsByModelEntity("Honda Civic");
		System.out.println(counter);
		
		carService.findCarsAfterYear(2000);
		System.out.println("Fin!!");
	}

}
