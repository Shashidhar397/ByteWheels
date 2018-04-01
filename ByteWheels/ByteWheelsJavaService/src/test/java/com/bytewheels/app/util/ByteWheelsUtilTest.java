package com.bytewheels.app.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.bytewheels.app.entity.Car;
import com.bytewheels.app.exception.SystemException;
import com.bytewheels.app.request.GetVehiclesRequest;
import com.bytewheels.app.request.VehicleBookingRequest;
import com.bytewheels.app.response.GetVehiclesResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ByteWheelsUtil.class})
public class ByteWheelsUtilTest {
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void testStringToDate() {
		try {
			Date date = ByteWheelsUtil.stringToDate("2018-03-31", ByteWheelsConstants.DEFAULT_DATE_FORMAT);
			Assert.assertNotNull(date);
		} catch (SystemException e) {
			Assert.fail("Failed to test testStringToDate");
		}
	}
	
	@Test
	public void testStringToDateSystemException() {
		try {
			Date date = ByteWheelsUtil.stringToDate("03-31-", ByteWheelsConstants.DEFAULT_DATE_FORMAT);
			Assert.fail("Failed to test testStringToDate");
		} catch (SystemException e) {
			
		}
	}
	
	@Test
	public void testMapResponse() {
		GetVehiclesResponse getVehiclesResponse = new GetVehiclesResponse();
		ByteWheelsUtil.mapResponse(prepareGetVehiclesRequest(), prepareCars(), getVehiclesResponse);
		Assert.assertEquals(0, getVehiclesResponse.getRetrunCode());
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
	
	private List<Car> prepareCars() {
		List<Car> cars = new ArrayList<Car>();
		Car car = new Car();
		car.setCarId("123");
		car.setCarRentCost(BigDecimal.valueOf(20));
		cars.add(car);
		return cars;
	}
	
}
