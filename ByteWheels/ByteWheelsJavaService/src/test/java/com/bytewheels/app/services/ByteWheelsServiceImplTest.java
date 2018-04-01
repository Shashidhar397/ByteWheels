package com.bytewheels.app.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.bytewheels.app.dao.ByteWheelsDaoIF;
import com.bytewheels.app.entity.BookedCars;
import com.bytewheels.app.entity.Car;
import com.bytewheels.app.exception.BusinessException;
import com.bytewheels.app.exception.SystemException;
import com.bytewheels.app.request.GetVehiclesRequest;
import com.bytewheels.app.request.VehicleBookingRequest;
import com.bytewheels.app.response.GetVehiclesResponse;
import com.bytewheels.app.response.VehicleBookingResponse;
import com.bytewheels.app.validator.RequestValidatorIF;

@RunWith(PowerMockRunner.class)
@PrepareForTest({JavaMailSender.class,ByteWheelsDaoIF.class})
public class ByteWheelsServiceImplTest {
	
	private ByteWheelsServiceImpl byteWheelsServiceImpl;
	private GetVehiclesRequest getVehicleRequest;
	private VehicleBookingRequest vehicleBookingRequest;
	private ByteWheelsDaoIF byteWheelsDao;
	private RequestValidatorIF requestValidator;
	private JavaMailSender mailSenderObj;
	
	@Before
	public void init() {
		byteWheelsServiceImpl = new ByteWheelsServiceImpl();
		setup();
	}
	
	private void setup() {
		try {
			prepareGetVehicleRequest();
			prepareVehicleBookingRequest();
			mockByteWheelsDao();
			mockRequestValidator();
			mockMailSenderObj();
			MemberModifier.field(ByteWheelsServiceImpl.class, "byteWheelDao").set(byteWheelsServiceImpl, byteWheelsDao);
			MemberModifier.field(ByteWheelsServiceImpl.class, "requestValidator").set(byteWheelsServiceImpl, requestValidator);
			MemberModifier.field(ByteWheelsServiceImpl.class, "mailSenderObj").set(byteWheelsServiceImpl, mailSenderObj);
		} catch(Exception e) {
			Assert.fail("failed to setup");
		}
	}
	
	@Test
	public void testGetVechicles() {
		try {
			requestValidator.validateGetVehiclesRequest(EasyMock.anyObject(GetVehiclesRequest.class));
			EasyMock.expectLastCall();
			EasyMock.replay(requestValidator);
			GetVehiclesResponse getVehiclesResponse = byteWheelsServiceImpl.getVechicles(getVehicleRequest);
			Assert.assertEquals(0, getVehiclesResponse.getRetrunCode());
		} catch (BusinessException e) {
			Assert.fail("Fail to test testGetVechicles");
		} catch (SystemException e) {
			Assert.fail("Fail to test testGetVechicles");
		}
		
	}
	
	@Test
	public void testGetVechiclesWithBusinessException() {
		try {
			requestValidator.validateGetVehiclesRequest(EasyMock.anyObject(GetVehiclesRequest.class));
			EasyMock.expectLastCall().andThrow(new BusinessException(1001, "Message"));
			EasyMock.replay(requestValidator);
			GetVehiclesResponse getVehiclesResponse = byteWheelsServiceImpl.getVechicles(getVehicleRequest);
			Assert.assertEquals(1001, getVehiclesResponse.getRetrunCode());
		} catch (BusinessException e) {
			Assert.fail("Fail to test testGetVechiclesWithBusinessException");
		} catch (SystemException e) {
			Assert.fail("Fail to test testGetVechiclesWithBusinessException");
		}
		
	}
	
	@Test
	public void testGetVechiclesWithSystemException() {
		try {
			requestValidator.validateGetVehiclesRequest(EasyMock.anyObject(GetVehiclesRequest.class));
			EasyMock.expectLastCall().andThrow(new SystemException(500, "Internal Server Error"));
			EasyMock.replay(requestValidator);
			GetVehiclesResponse getVehiclesResponse = byteWheelsServiceImpl.getVechicles(getVehicleRequest);
			Assert.assertEquals(500, getVehiclesResponse.getRetrunCode());
		} catch (BusinessException e) {
			Assert.fail("Fail to test testGetVechiclesWithSystemException");
		} catch (SystemException e) {
			Assert.fail("Fail to test testGetVechiclesWithSystemException");
		}
		
	}
	
	@Test
	public void testVehicleBookingRequest() {
		try {
			requestValidator.validateVehicleBookingRequest(EasyMock.anyObject(VehicleBookingRequest.class));
			EasyMock.expectLastCall();
			EasyMock.replay(requestValidator);
			VehicleBookingResponse vehicleBookingResponse = byteWheelsServiceImpl.bookVehicle(vehicleBookingRequest);
			Assert.assertEquals(0, vehicleBookingResponse.getReturnCode().intValue());
		} catch (BusinessException e) {
			Assert.fail("Fail to test testGetVechicles");
		} catch (SystemException e) {
			Assert.fail("Fail to test testGetVechicles");
		}
		
	}
	
	@Test
	public void testVehicleBookingRequestBusinessException() {
		try {
			requestValidator.validateVehicleBookingRequest(EasyMock.anyObject(VehicleBookingRequest.class));
			EasyMock.expectLastCall().andThrow(new BusinessException(1001, "Message"));
			EasyMock.replay(requestValidator);
			VehicleBookingResponse vehicleBookingResponse = byteWheelsServiceImpl.bookVehicle(vehicleBookingRequest);
			Assert.assertEquals(1001, vehicleBookingResponse.getReturnCode().intValue());
		} catch (BusinessException e) {
			Assert.fail("Fail to test testVehicleBookingRequestBusinessException");
		} catch (SystemException e) {
			Assert.fail("Fail to test testVehicleBookingRequestBusinessException");
		}
		
	}
	
	@Test
	public void testVehicleBookingRequestSystemException() {
		try {
			requestValidator.validateVehicleBookingRequest(EasyMock.anyObject(VehicleBookingRequest.class));
			EasyMock.expectLastCall().andThrow(new SystemException(500, "Message"));
			EasyMock.replay(requestValidator);
			VehicleBookingResponse vehicleBookingResponse = byteWheelsServiceImpl.bookVehicle(vehicleBookingRequest);
			Assert.assertEquals(500, vehicleBookingResponse.getReturnCode().intValue());
		} catch (BusinessException e) {
			Assert.fail("Fail to test testVehicleBookingRequestSystemException");
		} catch (SystemException e) {
			Assert.fail("Fail to test testVehicleBookingRequestSystemException");
		}
		
	}
	
	private void prepareGetVehicleRequest() {
		getVehicleRequest = new GetVehiclesRequest();
		getVehicleRequest.setCarName("Ford");
		getVehicleRequest.setCategory("COMPACT");
		getVehicleRequest.setCostFrom(BigDecimal.valueOf(10));
		getVehicleRequest.setRentingDateFrom("2020-03-20");
		getVehicleRequest.setRentingDateTo("2020-03-21");
	}
	
	private void prepareVehicleBookingRequest() {
		vehicleBookingRequest = new VehicleBookingRequest();
		vehicleBookingRequest.setCarName("Ford");
		vehicleBookingRequest.setCategory("COMPACT");
		vehicleBookingRequest.setUserEmailId("user@mail.com");
		vehicleBookingRequest.setFromDate("2020-03-20");
		vehicleBookingRequest.setToDate("2020-03-21");
	}
	
	private void mockByteWheelsDao() throws SystemException {
		byteWheelsDao = PowerMock.createMock(ByteWheelsDaoIF.class);
		EasyMock.expect(byteWheelsDao.getCars(EasyMock.anyString(), EasyMock.anyString(), EasyMock.anyString(), EasyMock.anyString(), EasyMock.anyObject(BigDecimal.class), EasyMock.anyObject(BigDecimal.class))).andReturn(prepareCars());
		EasyMock.expect(byteWheelsDao.persistBookedCar(EasyMock.anyObject(BookedCars.class))).andReturn(10);
		EasyMock.replay(byteWheelsDao);
	}
	
	private void mockMailSenderObj() {
		mailSenderObj = PowerMock.createMock(JavaMailSender.class);
		mailSenderObj.send(EasyMock.anyObject(MimeMessagePreparator.class));
		EasyMock.expectLastCall().anyTimes();
		EasyMock.replay(mailSenderObj);
	}
	
	private void mockRequestValidator() {
		requestValidator = PowerMock.createMock(RequestValidatorIF.class);
	}
	
	private List<Car> prepareCars() {
		List<Car> cars = new ArrayList<Car>();
		Car car = new Car();
		car.setCarId("123");
		car.setCarRentCost(BigDecimal.valueOf(20));
		cars.add(car);
		return cars;
	}
	
	@After
	public void tearDown() {
		
	}
}
