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

import com.bytewheels.app.request.VehicleBookingRequest;
import com.bytewheels.app.response.VehicleBookingResponse;
import com.bytewheels.app.services.ByteWheelsServiceIF;
import com.bytewheels.app.util.ByteWheelsConstants;
import com.bytewheels.app.util.ByteWheelsUtil;

@RestController
public class VehicleBookController {

	private static final Logger logger = Logger.getLogger(VehicleBookController.class);
	
	@Autowired
	private ByteWheelsServiceIF byteWheelsService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/bookVehicle.do", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public VehicleBookingResponse bookVehcle(final @RequestBody VehicleBookingRequest vehicleBookingRequest) throws Throwable {
		logger.info("Request got "+vehicleBookingRequest.getCarName());
		try {
			return byteWheelsService.bookVehicle(vehicleBookingRequest);
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@ExceptionHandler(value=Throwable.class)
	public ResponseEntity<VehicleBookingResponse> handleException() {
		logger.info("Internal Server error");
		VehicleBookingResponse vehicleBookingResponse = new VehicleBookingResponse();
		ByteWheelsUtil.prepareResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ByteWheelsConstants.INTERNAL_SERVER_ERROR_MESSAGE, vehicleBookingResponse);
		ResponseEntity<VehicleBookingResponse> responseEntity = new ResponseEntity<VehicleBookingResponse>(vehicleBookingResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}
	
	
}
