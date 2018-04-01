package com.bytewheels.app.validator;

import com.bytewheels.app.exception.BusinessException;
import com.bytewheels.app.exception.SystemException;
import com.bytewheels.app.request.GetVehiclesRequest;
import com.bytewheels.app.request.VehicleBookingRequest;

public interface RequestValidatorIF {

	public void validateGetVehiclesRequest(GetVehiclesRequest getVehiclesRequest) throws BusinessException, SystemException;
	
	public void validateVehicleBookingRequest(VehicleBookingRequest vehicleBookingRequest) throws BusinessException, SystemException;
}
