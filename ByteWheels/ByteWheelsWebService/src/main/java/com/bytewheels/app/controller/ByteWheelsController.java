package com.bytewheels.app.controller;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bytewheels.app.request.GetVehiclesRequest;
import com.bytewheels.app.request.VehicleBookingRequest;
import com.bytewheels.app.response.GetVehiclesResponse;
import com.bytewheels.app.response.VehicleBookingResponse;
import com.bytewheels.app.services.ByteWheelsServiceIF;
import com.bytewheels.app.util.ByteWheelsConstants;
import com.bytewheels.app.util.ByteWheelsUtil;

@RestController
public class ByteWheelsController {

	private static final Logger logger = Logger.getLogger(ByteWheelsController.class);
	
	@Autowired
	private ByteWheelsServiceIF byteWheelsService;
		
	@RequestMapping(method = RequestMethod.POST, value = "/listVehicles.do", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GetVehiclesResponse listVehicles(final @RequestBody GetVehiclesRequest rentCarRequest) throws Throwable {
		logger.info("Request got "+rentCarRequest.getRentingDateFrom());
		return byteWheelsService.getVechicles(rentCarRequest);
	}
	
	
	@ExceptionHandler(value=Throwable.class)
	public ResponseEntity<GetVehiclesResponse> handleException() {
		logger.info("Internal Server error");
		GetVehiclesResponse getVehiclesResponse = new GetVehiclesResponse();
		ByteWheelsUtil.prepareResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ByteWheelsConstants.INTERNAL_SERVER_ERROR_MESSAGE, getVehiclesResponse);
		ResponseEntity<GetVehiclesResponse> responseEntity = new ResponseEntity<GetVehiclesResponse>(getVehiclesResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}
	
}
