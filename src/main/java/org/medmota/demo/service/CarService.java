package org.medmota.demo.service;

import org.medmota.demo.repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

	private CarRepository carRepository;

	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public Integer getTotalCarsByModelEntity(String model) {
		return carRepository.getTotalCarsByModel(model);
	}
	
	public void findCarsAfterYear(int year) {
		carRepository.findCarsAfterYear(year);
	}

}
