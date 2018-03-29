package com.bytewheels.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

	public List<Car> getCars(String date, String category, int costFrom, int costTo){
		List<Object> carIds = null;
		List<String> bookedCarIds = null;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		if(date != null && !date.isEmpty()) {
			CriteriaQuery<Object> query = criteriaBuilder.createQuery(Object.class);
			Root<BookedCars> entity = query.from(BookedCars.class);
			
			query.select(entity.get("car_id")).where(criteriaBuilder.equal(entity.get("when_to_date"), date));
			carIds = entityManager.createQuery(query).getResultList();
			bookedCarIds = new ArrayList<String>();
			for(Object object : carIds) {
				bookedCarIds.add(new String((byte[])object));
			}
		}
		
		CriteriaQuery<Car> query = criteriaBuilder.createQuery(Car.class);
		Root<Car> fromCars = query.from(Car.class);
		
		query.select(fromCars);
		
		return entityManager.createQuery(query).getResultList();
	}
	
	
}
