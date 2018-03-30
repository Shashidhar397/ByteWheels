package com.bytewheels.app.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.bytewheels.app.entity.BookedCars;
import com.bytewheels.app.entity.Car;
import com.bytewheels.app.request.GetVehiclesRequest;
import com.bytewheels.app.request.VehicleBookingRequest;
import com.bytewheels.app.response.GetVehiclesResponse;
import com.bytewheels.app.response.Vehicle;
import com.bytewheels.app.response.VehicleBookingResponse;

public class ByteWheelsUtil {

	//Todo handle exception properly
	public static Date stringToDate(String dateString,String format) {
		if(dateString == null || dateString.isEmpty() || format == null || format.isEmpty()) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = null; 
		Date date = null;
		try {
			simpleDateFormat = new SimpleDateFormat(format);
			date = simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static GetVehiclesResponse mapResponse(GetVehiclesRequest getVehiclesRequest, List<Car> cars, GetVehiclesResponse getVehiclesResponse) {
		getVehiclesResponse.setRetrunCode(0);
		getVehiclesResponse.setReturnMessage(ByteWheelsConstants.SUCCESS_MESSAGE);
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Map<String, Vehicle> vehicleMap = new HashMap<String, Vehicle>();
		for(Car car : cars) {
			if(!vehicleMap.containsKey(car.getCarName()+"|"+car.getCarType())) {
				Vehicle vehicle = new Vehicle();
				vehicle.setCarName(car.getCarName());
				vehicle.setCarRentCost(car.getCarRentCost());
				vehicle.setCarType(car.getCarType());
				vehicle.setAvailableCars(1);
				vehicleMap.put(car.getCarName()+"|"+car.getCarType(), vehicle);
			} else {
				vehicleMap.get(car.getCarName()+"|"+car.getCarType()).setAvailableCars(vehicleMap.get(car.getCarName()+"|"+car.getCarType()).getAvailableCars()+1);
			}
			System.out.println(vehicleMap);
		}
		
		for(Entry<String, Vehicle> entry : vehicleMap.entrySet()) {
			vehicles.add(entry.getValue());
		}
		getVehiclesResponse.setVehicles(vehicles);
		return getVehiclesResponse;
	}
	
	public static void prepareResponseMessage(int returnCode, String returnMessage, GetVehiclesResponse getVehiclesResponse) {
		getVehiclesResponse.setRetrunCode(0);
		getVehiclesResponse.setReturnMessage(returnMessage);
	}
	
	public static void prepareResponseMessage(int returnCode, String returnMessage, VehicleBookingResponse vehicleBookingResponse) {
		vehicleBookingResponse.setReturnCode(0);
		vehicleBookingResponse.setReturnMessage(returnMessage);
	}
	
	public static BookedCars prepareBookCar(VehicleBookingRequest vehicleBookingRequest, Car car) {
		BookedCars bookedCars = new BookedCars();
		Date date = new Date();
		bookedCars.setBookedOnDate(new java.sql.Date(date.getTime()));
		bookedCars.setCarId(car.getCarId());
		bookedCars.setEntTs(date.toString());
		bookedCars.setUpdateTs(date.toString());
		bookedCars.setFromDate(new java.sql.Date(stringToDate(vehicleBookingRequest.getFromDate(), ByteWheelsConstants.DEFAULT_DATE_FORMAT).getTime()));
		bookedCars.setToDate(new java.sql.Date(stringToDate(vehicleBookingRequest.getToDate(), ByteWheelsConstants.DEFAULT_DATE_FORMAT).getTime()));
		bookedCars.setStat(BookingStatus.CONFIRM.getStat());
		bookedCars.setAmt(car.getCarRentCost().multiply(BigDecimal.valueOf(findDifferenceBetweenDates(vehicleBookingRequest.getFromDate(), vehicleBookingRequest.getToDate()))));
		bookedCars.setUserEmail(vehicleBookingRequest.getUserEmailId());
		return bookedCars;
	}
	
	public static int findDifferenceBetweenDates(String fromDateString, String toDateString) {
		Date fromDate = stringToDate(fromDateString, ByteWheelsConstants.DEFAULT_DATE_FORMAT);
		Date toDate = stringToDate(toDateString, ByteWheelsConstants.DEFAULT_DATE_FORMAT);
		long diff = toDate.getTime() - fromDate.getTime();
		System.out.println(diff);
	    System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
	    return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+1;
	}
	
	public static void mapVehicleBookingResponse(BookedCars bookedCars, VehicleBookingResponse vehicleBookingResponse) {
		vehicleBookingResponse.setCarId(bookedCars.getCarId());
		vehicleBookingResponse.setCost(bookedCars.getAmt().doubleValue());
		vehicleBookingResponse.setFromDate(bookedCars.getFromDate().toString());
		vehicleBookingResponse.setToDate(bookedCars.getToDate().toString());
		vehicleBookingResponse.setInvoiceId(bookedCars.getBookingId());
		vehicleBookingResponse.setReturnCode(0);
		vehicleBookingResponse.setReturnMessage(ByteWheelsConstants.SUCCESS_MESSAGE);
		vehicleBookingResponse.setUserEmailId(bookedCars.getUserEmail());
		
	}
	
}
