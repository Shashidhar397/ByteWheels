package com.bytewheels.app.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytewheels.app.dao.VehicleDaoIF;

@Service
@Transactional
public class ByteWheelsServiceImpl implements ByteWheelsServiceIF{
	
	private static final Logger logger = Logger.getLogger(ByteWheelsServiceImpl.class);
	
	@Autowired
	private VehicleDaoIF vehicleDao;
	
	
	public String process() {
		logger.info("Hello World is working");
		vehicleDao.persistVehicle();
		return "Hello World";
	}

}
