package com.bytewheels.app.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytewheels.app.dao.ByteWheelsDaoIF;
import com.bytewheels.app.entity.Car;
import com.bytewheels.app.request.RentCarRequest;
import com.bytewheels.app.response.RentCarResponse;

@Service
//@Transactional
public class ByteWheelsServiceImpl implements ByteWheelsServiceIF{
	
	private static final Logger logger = Logger.getLogger(ByteWheelsServiceImpl.class);
	
	@Autowired
	private ByteWheelsDaoIF byteWheelDao;
	
	
	public RentCarResponse getVechicles(RentCarRequest rentCarRequest) {
		RentCarResponse rentCarResponse = new RentCarResponse();
		rentCarResponse.setAvailableDate("2018/03/22");
		List<Car> cars = byteWheelDao.getCars(null, null, 0, 0);
		System.out.println("cars : "+cars.get(0).getCarId());
		return rentCarResponse;
	}

}
