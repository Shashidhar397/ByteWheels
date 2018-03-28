package com.bytewheels.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bytewheels.app.request.RentCarRequest;
import com.bytewheels.app.services.ByteWheelsServiceIF;

@Controller
public class ByteWheelsController {

	private static final Logger logger = Logger.getLogger(ByteWheelsController.class);
	
	@Autowired
	private ByteWheelsServiceIF byteWheelsService;
	
	@RequestMapping(value = "/listVehicles", method = RequestMethod.POST)
	@ResponseBody
	public String printWelcome(@RequestBody RentCarRequest rentCarRequest) {
		logger.info("Request got "+rentCarRequest.getCategory());
		return byteWheelsService.process();
	}
	
}
