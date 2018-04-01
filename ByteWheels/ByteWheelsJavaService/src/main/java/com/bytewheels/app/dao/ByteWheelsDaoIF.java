package com.bytewheels.app.dao;

import java.math.BigDecimal;
import java.util.List;

import com.bytewheels.app.entity.BookedCars;
import com.bytewheels.app.entity.Car;
import com.bytewheels.app.exception.SystemException;

public interface ByteWheelsDaoIF {

	public int persistBookedCar(BookedCars bookedCar);
	
	public List<Car> getCars(String dateFrom, String dateTo, String category,  String carName, BigDecimal costFrom, BigDecimal costTo) throws SystemException;
}
