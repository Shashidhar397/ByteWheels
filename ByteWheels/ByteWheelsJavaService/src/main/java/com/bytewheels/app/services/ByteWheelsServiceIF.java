package com.bytewheels.app.services;

import com.bytewheels.app.request.GetVehiclesRequest;
import com.bytewheels.app.request.VehicleBookingRequest;
import com.bytewheels.app.response.GetVehiclesResponse;
import com.bytewheels.app.response.VehicleBookingResponse;

public interface ByteWheelsServiceIF {
	
	public GetVehiclesResponse getVechicles(GetVehiclesRequest rentCarRequest);
	
	public VehicleBookingResponse bookVehicle(VehicleBookingRequest vehicleBookingRequest);
	
}
