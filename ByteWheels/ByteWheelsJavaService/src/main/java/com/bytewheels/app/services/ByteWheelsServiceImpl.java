package com.bytewheels.app.services;

import java.math.BigDecimal;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytewheels.app.dao.ByteWheelsDaoIF;
import com.bytewheels.app.entity.BookedCars;
import com.bytewheels.app.entity.Car;
import com.bytewheels.app.exception.BusinessException;
import com.bytewheels.app.exception.SystemException;
import com.bytewheels.app.request.GetVehiclesRequest;
import com.bytewheels.app.request.VehicleBookingRequest;
import com.bytewheels.app.response.GetVehiclesResponse;
import com.bytewheels.app.response.VehicleBookingResponse;
import com.bytewheels.app.util.ByteWheelsConstants;
import com.bytewheels.app.util.ByteWheelsUtil;
import com.bytewheels.app.validator.RequestValidatorIF;

@Service
public class ByteWheelsServiceImpl implements ByteWheelsServiceIF{
	
	private static final Logger logger = Logger.getLogger(ByteWheelsServiceImpl.class);
	
	@Autowired
	private ByteWheelsDaoIF byteWheelDao;
	
	@Autowired
	private JavaMailSender mailSenderObj;
	
	@Autowired
	private RequestValidatorIF requestValidator;
	
	public GetVehiclesResponse getVechicles(GetVehiclesRequest getVehiclesRequest) {
		GetVehiclesResponse getVehiclesResponse = new GetVehiclesResponse();
		try {
			requestValidator.validateGetVehiclesRequest(getVehiclesRequest);		
			
			if((getVehiclesRequest.getRentingDateFrom() == null || getVehiclesRequest.getRentingDateFrom().isEmpty()) && (getVehiclesRequest.getRentingDateTo() != null && !getVehiclesRequest.getRentingDateTo().isEmpty())) {
				getVehiclesRequest.setRentingDateFrom(getVehiclesRequest.getRentingDateTo());
			} else if((getVehiclesRequest.getRentingDateTo() == null || getVehiclesRequest.getRentingDateTo().isEmpty()) && (getVehiclesRequest.getRentingDateFrom() != null && !getVehiclesRequest.getRentingDateFrom().isEmpty())) {
				getVehiclesRequest.setRentingDateTo(getVehiclesRequest.getRentingDateFrom());
			}
			if((getVehiclesRequest.getRentingDateFrom() != null && !getVehiclesRequest.getRentingDateFrom().isEmpty()) && (getVehiclesRequest.getRentingDateTo() != null && !getVehiclesRequest.getRentingDateTo().isEmpty())) {
				getVehiclesResponse.setAvailableDate(getVehiclesRequest.getRentingDateFrom() + " - " + getVehiclesRequest.getRentingDateTo());
			}
			System.out.println(getVehiclesRequest.getRentingDateFrom()+" "+ getVehiclesRequest.getRentingDateTo());
			List<Car> cars = byteWheelDao.getCars(getVehiclesRequest.getRentingDateFrom(), getVehiclesRequest.getRentingDateTo(), getVehiclesRequest.getCategory(), getVehiclesRequest.getCarName(), getVehiclesRequest.getCostFrom(), getVehiclesRequest.getCostTo());
			System.out.println("Available cars : ");
			if(cars != null && !cars.isEmpty()) {
				ByteWheelsUtil.mapResponse(getVehiclesRequest, cars, getVehiclesResponse);
			} else {
				ByteWheelsUtil.prepareResponseMessage(1, ByteWheelsConstants.DATA_NOT_FOUND, getVehiclesResponse);
			}
		} catch(BusinessException e) {
			ByteWheelsUtil.prepareResponseMessage(e.getErrorCode(), e.getMessage(), getVehiclesResponse);
		} catch(SystemException e) {
			ByteWheelsUtil.prepareResponseMessage(e.getErrorCode(), e.getMessage(), getVehiclesResponse);
		}
		return getVehiclesResponse;
	}


	@Transactional(rollbackFor=Throwable.class)
	public VehicleBookingResponse bookVehicle(VehicleBookingRequest vehicleBookingRequest) {
		VehicleBookingResponse vehicleBookingResponse = new VehicleBookingResponse();
		try {
			requestValidator.validateVehicleBookingRequest(vehicleBookingRequest);	
			List<Car> cars = byteWheelDao.getCars(vehicleBookingRequest.getFromDate(), vehicleBookingRequest.getToDate(), vehicleBookingRequest.getCategory(), vehicleBookingRequest.getCarName(), BigDecimal.ZERO, BigDecimal.ZERO);
			if(cars != null && !cars.isEmpty()) {
				BookedCars bookedCar = ByteWheelsUtil.prepareBookCar(vehicleBookingRequest, cars.get(0));
				int invoiceId = byteWheelDao.persistBookedCar(bookedCar);
				System.out.println("Invoice Id : "+invoiceId);
				
				ByteWheelsUtil.mapVehicleBookingResponse(bookedCar, vehicleBookingRequest, vehicleBookingResponse);
				sendEmail(vehicleBookingResponse);
			}
			else {
				ByteWheelsUtil.prepareResponseMessage(1, ByteWheelsConstants.VEHICLE_NOT_AVAILABLE, vehicleBookingResponse);
			}
		} catch(BusinessException e) {
			ByteWheelsUtil.prepareResponseMessage(e.getErrorCode(), e.getMessage(), vehicleBookingResponse);
		} catch(SystemException e) {
			ByteWheelsUtil.prepareResponseMessage(e.getErrorCode(), e.getMessage(), vehicleBookingResponse);
		}
		
		return vehicleBookingResponse;
	}
	
	private void sendEmail(final VehicleBookingResponse vehicleBookingResponse) {
		mailSenderObj.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");				
				mimeMsgHelperObj.setTo(vehicleBookingResponse.getUserEmailId());	
				mimeMsgHelperObj.setText("Your booking with ByteWheels is successful amt needs to be paid at the pick up is : "+vehicleBookingResponse.getCost()+"$");
				mimeMsgHelperObj.setSubject("ByteWheels Invoice");

			}
		});
	}

}
