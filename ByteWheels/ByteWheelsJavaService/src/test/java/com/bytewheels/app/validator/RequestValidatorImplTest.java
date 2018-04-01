package com.bytewheels.app.validator;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.bytewheels.app.exception.BusinessException;
import com.bytewheels.app.exception.SystemException;
import com.bytewheels.app.request.GetVehiclesRequest;
import com.bytewheels.app.request.VehicleBookingRequest;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RequestValidatorImpl.class})
public class RequestValidatorImplTest {

	private RequestValidatorImpl requestValidatorImpl;
	
	@Before
	public void init() {
		requestValidatorImpl = new RequestValidatorImpl();
	}
	
	@Test
	public void testValidateGetVehiclesRequest() {
		GetVehiclesRequest getVehiclesRequest = prepareGetVehiclesRequest();
		try {
			requestValidatorImpl.validateGetVehiclesRequest(getVehiclesRequest);
		} catch (BusinessException e) {
			Assert.fail("fail to test testValidateGetVehiclesRequest");
		} catch (SystemException e) {
			Assert.fail("fail to test testValidateGetVehiclesRequest");
		}
	}
	
	@Test
	public void testValidateGetVehiclesRequestBusinessException() {
		GetVehiclesRequest getVehiclesRequest = new GetVehiclesRequest();
		try {
			requestValidatorImpl.validateGetVehiclesRequest(getVehiclesRequest);
			Assert.fail("fail to test testValidateGetVehiclesRequest");
		} catch (BusinessException e) {
			
		} catch (SystemException e) {
			Assert.fail("fail to test testValidateGetVehiclesRequest");
		}
	}
	
	@Test
	public void testValidateGetVehiclesRequestInvalidDate() {
		GetVehiclesRequest getVehiclesRequest = prepareGetVehiclesRequest();
		getVehiclesRequest.setRentingDateFrom("2017-03-27");
		try {
			requestValidatorImpl.validateGetVehiclesRequest(getVehiclesRequest);
			Assert.fail("fail to test testValidateGetVehiclesRequest");
		} catch (BusinessException e) {
			
		} catch (SystemException e) {
			Assert.fail("fail to test testValidateGetVehiclesRequest");
		}
	}
	
	@Test
	public void testValidateVehicleBookingRequest() {
		VehicleBookingRequest vehicleBookingRequest = prepareVehicleBookingRequest();
		try {
			requestValidatorImpl.validateVehicleBookingRequest(vehicleBookingRequest);
		} catch (BusinessException e) {
			Assert.fail("fail to test testValidateVehicleBookingRequest");
		} catch (SystemException e) {
			Assert.fail("fail to test testValidateVehicleBookingRequest");
		}
	}
	
	@Test
	public void testValidateVehicleBookingRequestBusinessException() {
		VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest();
		try {
			requestValidatorImpl.validateVehicleBookingRequest(vehicleBookingRequest);
			Assert.fail("fail to test testValidateVehicleBookingRequestBusinessException");
		} catch (BusinessException e) {
			
		} catch (SystemException e) {
			Assert.fail("fail to test testValidateVehicleBookingRequestBusinessException");
		}
	}
	
	@Test
	public void testValidateVehicleBookingRequestInvalidDate() {
		VehicleBookingRequest vehicleBookingRequest = prepareVehicleBookingRequest();
		vehicleBookingRequest.setFromDate("2017-03-27");
		try {
			requestValidatorImpl.validateVehicleBookingRequest(vehicleBookingRequest);
			Assert.fail("fail to test testValidateGetVehiclesRequest");
		} catch (BusinessException e) {
			
		} catch (SystemException e) {
			Assert.fail("fail to test testValidateGetVehiclesRequest");
		}
	}
	
	@Test
	public void testValidateVehicleBookingRequestInvalidEmail() {
		VehicleBookingRequest vehicleBookingRequest = prepareVehicleBookingRequest();
		vehicleBookingRequest.setUserEmailId("");
		try {
			requestValidatorImpl.validateVehicleBookingRequest(vehicleBookingRequest);
			Assert.fail("fail to test testValidateGetVehiclesRequest");
		} catch (BusinessException e) {
			
		} catch (SystemException e) {
			Assert.fail("fail to test testValidateGetVehiclesRequest");
		}
	}
	
	public GetVehiclesRequest prepareGetVehiclesRequest() {
		GetVehiclesRequest getVehiclesRequest = new GetVehiclesRequest();
		getVehiclesRequest.setCarName("carname");
		getVehiclesRequest.setCategory("COMPACT");
		getVehiclesRequest.setCostFrom(BigDecimal.valueOf(10));
		getVehiclesRequest.setCostTo(BigDecimal.valueOf(20));
		getVehiclesRequest.setRentingDateFrom("2018-05-03");
		getVehiclesRequest.setRentingDateFrom("2018-05-05");
		return getVehiclesRequest;
	}
	
	public VehicleBookingRequest prepareVehicleBookingRequest() {
		VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest();
		vehicleBookingRequest.setCarName("carname");
		vehicleBookingRequest.setCategory("COMPACT");
		vehicleBookingRequest.setUserEmailId("user@gmail.com");
		vehicleBookingRequest.setFromDate("2018-05-03");
		vehicleBookingRequest.setToDate("2018-05-05");
		return vehicleBookingRequest;
	}
	
	@After
	public void tearDown() {
		requestValidatorImpl = null;
	}
	
}
