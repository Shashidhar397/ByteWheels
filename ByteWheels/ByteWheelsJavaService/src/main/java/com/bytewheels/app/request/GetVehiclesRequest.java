package com.bytewheels.app.request;

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
"rentingDateFrom",
"rentingDateTo",
"category",
"costFrom",
"costTo",
"carName"
})
public class GetVehiclesRequest {

@JsonProperty("rentingDateFrom")
private String rentingDateFrom;
@JsonProperty("rentingDateTo")
private String rentingDateTo;
@JsonProperty("category")
private String category;
@JsonProperty("costFrom")
private BigDecimal costFrom = BigDecimal.ZERO;
@JsonProperty("costTo")
private BigDecimal costTo = BigDecimal.ZERO;
@JsonProperty("carName")
private String carName;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("rentingDateFrom")
public String getRentingDateFrom() {
return rentingDateFrom;
}

@JsonProperty("rentingDateFrom")
public void setRentingDateFrom(String rentingDate) {
this.rentingDateFrom = rentingDate;
}

@JsonProperty("rentingDateTo")
public String getRentingDateTo() {
	return rentingDateTo;
}

@JsonProperty("rentingDateTo")
public void setRentingDateTo(String rentingDateTo) {
	this.rentingDateTo = rentingDateTo;
}

@JsonProperty("category")
public String getCategory() {
return category;
}

@JsonProperty("category")
public void setCategory(String category) {
this.category = category;
}

@JsonProperty("costFrom")
public BigDecimal getCostFrom() {
return costFrom;
}

@JsonProperty("costFrom")
public void setCostFrom(BigDecimal costFrom) {
this.costFrom = costFrom;
}

@JsonProperty("costTo")
public BigDecimal getCostTo() {
return costTo;
}

@JsonProperty("costTo")
public void setCostTo(BigDecimal costTo) {
this.costTo = costTo;
}

public String getCarName() {
	return carName;
}

public void setCarName(String carName) {
	this.carName = carName;
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
