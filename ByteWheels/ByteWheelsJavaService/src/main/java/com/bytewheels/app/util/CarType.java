package com.bytewheels.app.util;

public enum CarType {

	COMPACT("COMPACT"), 
	FULL("FULL"), 
	LARGE("LARGE"), 
	LUXURY("LUXURY");
	
	private String value;
	
	private CarType(String value) {
		this.value = value;
	}
	
	public String getCompactType()
    {
        return this.value;
    }
}
