package com.bytewheels.app.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booked_cars")
public class BookedCars {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private int bookingId;
	
	@Column(name = "car_id")
	private String carId;
	
	@Column(name = "booked_on_date")
	private Date bookedOnDate;
	
	@Column(name = "when_to_date")
	private Date whenToDate;
	
	@Column(name = "ent_ts")
	private String entTs;
	
	@Column(name = "update_ts")
	private String updateTs;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public Date getBookedOnDate() {
		return bookedOnDate;
	}

	public void setBookedOnDate(Date bookedOnDate) {
		this.bookedOnDate = bookedOnDate;
	}

	public Date getWhenToDate() {
		return whenToDate;
	}

	public void setWhenToDate(Date whenToDate) {
		this.whenToDate = whenToDate;
	}

	public String getEntTs() {
		return entTs;
	}

	public void setEntTs(String entTs) {
		this.entTs = entTs;
	}

	public String getUpdateTs() {
		return updateTs;
	}

	public void setUpdateTs(String updateTs) {
		this.updateTs = updateTs;
	}
	
	
	
}
