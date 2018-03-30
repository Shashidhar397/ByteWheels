package com.bytewheels.app.util;

public enum BookingStatus {
	
	SELECTED(1), 
	CONFIRM(2), 
	CANCEL(3);
	
	private int value;
	
	private BookingStatus(int value) {
		this.value = value;
	}
	
	public int getStat()
    {
        return this.value;
    }
	
}
