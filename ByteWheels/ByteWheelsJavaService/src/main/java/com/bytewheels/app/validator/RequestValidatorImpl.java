package com.bytewheels.app.validator;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.bytewheels.app.exception.BusinessException;
import com.bytewheels.app.exception.SystemException;
import com.bytewheels.app.request.GetVehiclesRequest;
import com.bytewheels.app.request.VehicleBookingRequest;
import com.bytewheels.app.util.ByteWheelsConstants;
import com.bytewheels.app.util.ByteWheelsUtil;

@Component
public class RequestValidatorImpl implements RequestValidatorIF{
	
	private static final Logger logger = Logger.getLogger(RequestValidatorImpl.class);

	public void validateGetVehiclesRequest(GetVehiclesRequest getVehiclesRequest) throws BusinessException, SystemException {
		if(getVehiclesRequest == null) {
			logger.error("Invalid GetVehiclesRequest Request");
			throw new BusinessException(10001, "Invalid Request");
		} else if((getVehiclesRequest.getCarName() == null || getVehiclesRequest.getCarName().isEmpty())
				&& (getVehiclesRequest.getCategory() == null || getVehiclesRequest.getCategory().isEmpty())
				&& (getVehiclesRequest.getCostFrom() == null || getVehiclesRequest.getCostFrom().equals(BigDecimal.ZERO))
				&& (getVehiclesRequest.getCostTo() == null || getVehiclesRequest.getCostTo().equals(BigDecimal.ZERO))
				&& (getVehiclesRequest.getRentingDateFrom() == null || getVehiclesRequest.getRentingDateFrom().isEmpty())
				&& (getVehiclesRequest.getRentingDateTo() == null || getVehiclesRequest.getRentingDateTo().isEmpty())) {
			logger.error("Invalid GetVehiclesRequest Request");
			throw new BusinessException(10001, "Invalid Request");
		}
		
		if(getVehiclesRequest.getRentingDateFrom() != null && !getVehiclesRequest.getRentingDateFrom().isEmpty()) {
			Date date = ByteWheelsUtil.stringToDate(getVehiclesRequest.getRentingDateFrom(), ByteWheelsConstants.DEFAULT_DATE_FORMAT);
			if(date.getTime() < new Date().getTime()) {
				logger.error("Invalid Date");
				throw new BusinessException(10005, "Invalid Date");
			}
		}
		
		if(getVehiclesRequest.getRentingDateTo() != null && !getVehiclesRequest.getRentingDateTo().isEmpty()) {
			Date date = ByteWheelsUtil.stringToDate(getVehiclesRequest.getRentingDateTo(), ByteWheelsConstants.DEFAULT_DATE_FORMAT);
			if(date.getTime() < new Date().getTime()) {
				logger.error("Invalid Date");
				throw new BusinessException(10005, "Invalid Date");
			}
		}
	}
	
	public void validateVehicleBookingRequest(VehicleBookingRequest vehicleBookingRequest) throws BusinessException, SystemException {
		if(vehicleBookingRequest == null) {
			logger.error("Invalid VehicleBookingRequest Request");
			throw new BusinessException(10002, "Invalid Request For Booking");
		} else if(vehicleBookingRequest.getCarName() == null || vehicleBookingRequest.getCarName().isEmpty()) {
			logger.error("Invalid Request Car Name is mandatory");
			throw new BusinessException(10003, "Invalid Request Car Name is mandatory");
		} 
		if(vehicleBookingRequest.getFromDate() == null || vehicleBookingRequest.getFromDate().isEmpty() || vehicleBookingRequest.getToDate() == null || vehicleBookingRequest.getToDate().isEmpty()) {
			logger.error("Invalid Request From date and end date is required");
			throw new BusinessException(10004, "Invalid Request From date and end date is required");
		}
		if(vehicleBookingRequest.getUserEmailId() == null || vehicleBookingRequest.getUserEmailId().isEmpty()) {
			logger.error("Invalid Request User email id is required for confirm booking");
			throw new BusinessException(10004, "Invalid Request User email id is required for confirm booking");
		}
		if(vehicleBookingRequest.getFromDate() != null && !vehicleBookingRequest.getFromDate().isEmpty()) {
			Date date = ByteWheelsUtil.stringToDate(vehicleBookingRequest.getFromDate(), ByteWheelsConstants.DEFAULT_DATE_FORMAT);
			if(date.getTime() < new Date().getTime()) {
				logger.error("Invalid Date");
				throw new BusinessException(10005, "Invalid Date");
			}
		}
		if(vehicleBookingRequest.getToDate() != null && !vehicleBookingRequest.getToDate().isEmpty()) {
			Date date = ByteWheelsUtil.stringToDate(vehicleBookingRequest.getToDate(), ByteWheelsConstants.DEFAULT_DATE_FORMAT);
			if(date.getTime() < new Date().getTime()) {
				logger.error("Invalid Date");
				throw new BusinessException(10005, "Invalid Date");
			}
		}
	}
	
}
