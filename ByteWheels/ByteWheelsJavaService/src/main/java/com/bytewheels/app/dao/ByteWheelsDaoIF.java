package com.bytewheels.app.dao;

import java.math.BigDecimal;
import java.util.List;

import com.bytewheels.app.entity.BookedCars;
import com.bytewheels.app.entity.Car;

public interface ByteWheelsDaoIF {

	public void persistCar();
	public int persistBookedCar(BookedCars bookedCar);
	public void persistUser();
	
	public List<Car> getCars(String dateFrom, String dateTo, String category,  String carName, BigDecimal costFrom, BigDecimal costTo);
}
