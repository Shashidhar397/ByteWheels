package com.bytewheels.app.dao;

import java.util.List;

import com.bytewheels.app.entity.Car;

public interface ByteWheelsDaoIF {

	public void persistCar();
	public void persistBookedCar();
	public void persistUser();
	
	public List<Car> getCars(String date, String category, int costFrom, int costTo);
}
