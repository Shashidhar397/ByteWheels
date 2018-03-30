package com.bytewheels.app.entity;

import java.math.BigDecimal;
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
	
	@Column(name = "ent_ts")
	private String entTs;
	
	@Column(name = "update_ts")
	private String updateTs;
	
	private int stat;
	
	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_Date")
	private Date toDate;
	
	@Column(name = "user_email")
	private String userEmail;
	
	private BigDecimal amt;
	
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

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}	
		
}
