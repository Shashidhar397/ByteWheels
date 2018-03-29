package com.bytewheels.app.controller;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bytewheels.app.request.RentCarRequest;
import com.bytewheels.app.response.RentCarResponse;
import com.bytewheels.app.services.ByteWheelsServiceIF;

@RestController
public class ByteWheelsController {

	private static final Logger logger = Logger.getLogger(ByteWheelsController.class);
	
	@Autowired
	private ByteWheelsServiceIF byteWheelsService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/listVehicles", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public RentCarResponse listVehicles(final @RequestBody RentCarRequest rentCarRequest) {
		logger.info("Request got "+rentCarRequest.getRentingDate());
		return byteWheelsService.getVechicles(rentCarRequest);
	}
	
}
