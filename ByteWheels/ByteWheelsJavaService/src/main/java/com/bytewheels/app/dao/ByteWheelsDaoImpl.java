package com.bytewheels.app.dao;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bytewheels.app.entity.BookedCars;
import com.bytewheels.app.entity.Car;
import com.bytewheels.app.entity.User;

@Repository
@Transactional
public class ByteWheelsDaoImpl implements ByteWheelsDaoIF{
	
	@PersistenceContext(unitName="vehiclePersistenceUnit")
	EntityManager entityManager;

	public void persistCar() {
		Random rand = new Random();
		 
        // Generate random integers in range 0 to 999
        int rand_int1 = rand.nextInt(1000);
		Car car = new Car();
		car.setCarId(String.valueOf(rand_int1));
		car.setCarName("car1");
		entityManager.persist(car);
	}

	public void persistBookedCar() {
		BookedCars bookedCars = new BookedCars();
		bookedCars.setCarId("1");
		entityManager.persist(bookedCars);
		entityManager.flush();
	}

	public void persistUser() {
		User user = new User();
		user.setInvoice_id(1);
		entityManager.persist(user);
		entityManager.flush();
	}

	
	
	
}
