package com.bytewheels.app.response;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"carId",
"carName",
"carType",
"carRentCost"
})
public class Vehicle {

@JsonProperty("carName")
private String carName;
@JsonProperty("carType")
private String carType;
@JsonProperty("carRentCost")
private BigDecimal carRentCost;
@JsonProperty("availableCars")
private int availableCars;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("carName")
public String getCarName() {
return carName;
}

@JsonProperty("carName")
public void setCarName(String carName) {
this.carName = carName;
}

@JsonProperty("carType")
public String getCarType() {
return carType;
}

@JsonProperty("carType")
public void setCarType(String carType) {
this.carType = carType;
}

@JsonProperty("carRentCost")
public BigDecimal getCarRentCost() {
return carRentCost;
}

@JsonProperty("carRentCost")
public void setCarRentCost(BigDecimal carRentCost) {
this.carRentCost = carRentCost;
}


@JsonProperty("availableCars")
public int getAvailableCars() {
	return availableCars;
}

@JsonProperty("availableCars")
public void setAvailableCars(int availableCars) {
	this.availableCars = availableCars;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}