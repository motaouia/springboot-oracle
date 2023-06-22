package org.medmota.demo.repositories;

import org.medmota.demo.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	@Procedure(value = "GET_TOTAL_CARS_BY_MODEL", outputParameterName = "count_out")
	Integer getTotalCarsByModel(@Param("model_in") String model);

	@Procedure(value = "FIND_CARS_AFTER_YEAR")
	void findCarsAfterYear(int year);
}
