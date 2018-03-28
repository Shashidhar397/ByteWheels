package com.bytewheels.app.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="car")
public class Car {

	@Id
	@Column(name = "car_id")
	private String carId;
	
	@Column(name = "car_name")
	private String carName;
	
	@Column(name = "car_type")
	private String carType;
		
	@Column(name = "car_rent_cost")
	private BigDecimal carRentCost;
	
	@Column(name = "total_cars")
	private int totalCars;
	
	@Column(name = "available_cars")
	private int availableCars;
	
	@Column(name = "ent_ts")
	private String ent_ts;
	
	@Column(name = "update_ts")
	private String update_ts;

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public BigDecimal getCarRentCost() {
		return carRentCost;
	}

	public void setCarRentCost(BigDecimal carRentCost) {
		this.carRentCost = carRentCost;
	}

	public int getTotalCars() {
		return totalCars;
	}

	public void setTotalCars(int totalCars) {
		this.totalCars = totalCars;
	}

	public int getAvailableCars() {
		return availableCars;
	}

	public void setAvailableCars(int availableCars) {
		this.availableCars = availableCars;
	}

	public String getEnt_ts() {
		return ent_ts;
	}

	public void setEnt_ts(String ent_ts) {
		this.ent_ts = ent_ts;
	}

	public String getUpdate_ts() {
		return update_ts;
	}

	public void setUpdate_ts(String update_ts) {
		this.update_ts = update_ts;
	}
	
}
