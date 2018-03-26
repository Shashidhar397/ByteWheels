package com.bytewheels.app.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ByteWheelsServiceImpl implements ByteWheelsServiceIF{
	
	private static final Logger logger = Logger.getLogger(ByteWheelsServiceImpl.class);
	
	public String process() {
		logger.info("Hello World is working");
		return "Hello World";
	}

}
