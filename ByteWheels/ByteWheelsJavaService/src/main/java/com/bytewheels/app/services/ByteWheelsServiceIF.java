package com.bytewheels.app.services;

import com.bytewheels.app.request.RentCarRequest;
import com.bytewheels.app.response.RentCarResponse;

public interface ByteWheelsServiceIF {
	
	public RentCarResponse getVechicles(RentCarRequest rentCarRequest);
	
}
