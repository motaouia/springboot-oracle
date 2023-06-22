package org.medmota.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARS")

/*@NamedStoredProcedureQuery(name = "Car.getTotalCarsByModel", procedureName = "GET_TOTAL_CARS_BY_MODEL", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "model_in", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "count_out", type = Integer.class) })
*/
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private String model;

	@Column
	private Integer year;

	public Car() {
		super();
	}

	public Car(String model, Integer year) {
		super();
		this.model = model;
		this.year = year;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", year=" + year + "]";
	}

}
