package com.bytewheels.app.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bytewheels.app.entity.BookedCars;
import com.bytewheels.app.entity.Car;
import com.bytewheels.app.entity.User;
import com.bytewheels.app.util.BookingStatus;
import com.bytewheels.app.util.ByteWheelsConstants;
import com.bytewheels.app.util.ByteWheelsUtil;

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

	public int persistBookedCar(BookedCars bookedCar) {
		entityManager.persist(bookedCar);
		entityManager.flush();
		return bookedCar.getBookingId();
	}

	public void persistUser() {
		User user = new User();
		user.setInvoice_id(1);
		entityManager.persist(user);
		entityManager.flush();
	}

	public List<Car> getCars(String dateFrom, String dateTo, String category, String carName, BigDecimal costFrom, BigDecimal costTo){
		List<Object> carIds = null;
		List<String> bookedCarIds = null;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		if((dateFrom != null && !dateFrom.isEmpty()) && ((dateTo != null && !dateTo.isEmpty()))) {
			CriteriaQuery<Object> query = criteriaBuilder.createQuery(Object.class);
			Root<BookedCars> entity = query.from(BookedCars.class);
			Expression<Date> fromDate = entity.get("fromDate");
			Expression<Date> toDate = entity.get("toDate");
			query.select(entity.get("carId")).where(criteriaBuilder.greaterThanOrEqualTo(fromDate, ByteWheelsUtil.stringToDate(dateFrom, ByteWheelsConstants.DEFAULT_DATE_FORMAT)),criteriaBuilder.lessThanOrEqualTo(toDate, ByteWheelsUtil.stringToDate(dateTo, ByteWheelsConstants.DEFAULT_DATE_FORMAT)), criteriaBuilder.equal(entity.get("stat"),BookingStatus.CONFIRM.getStat()));
			carIds = entityManager.createQuery(query).getResultList();
			bookedCarIds = new ArrayList<String>();
			for(Object object : carIds) {
				bookedCarIds.add((String)object);
			}
			System.out.println("carIds"+bookedCarIds);
		}
		
		CriteriaQuery<Car> query = criteriaBuilder.createQuery(Car.class);
		Root<Car> fromCars = query.from(Car.class);
		query.select(fromCars);
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(bookedCarIds != null && !bookedCarIds.isEmpty()) {
			Expression<String> expressionForBookedCars = fromCars.get("carId");
			Predicate bookedCarPredicate = expressionForBookedCars.in(bookedCarIds);
			predicates.add(criteriaBuilder.not(bookedCarPredicate));		
		}
		
		if(category != null && !category.isEmpty()) {
			Expression<String> expCarType = fromCars.get("carType");
			predicates.add(criteriaBuilder.equal(expCarType, category.toUpperCase()));
		}
		
		if(carName != null && !carName.isEmpty()) {
			Expression<String> expCarName = fromCars.get("carName");
			predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(expCarName), carName.toUpperCase()));
		}
		
		Expression<BigDecimal> carCost = fromCars.get("carRentCost");
		
		if(costFrom.intValue() > 0) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(carCost, costFrom));
		}
		
		if(costTo.intValue() > 0) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(carCost, costTo));
		}
		
		if(predicates != null && !predicates.isEmpty() ) {
			query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		}
		
		
		
		return entityManager.createQuery(query).getResultList();
	}
	
	
}
