package com.bytewheels.app.response;

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
"returnCode",
"returnMessage",
"invoiceId",
"carId",
"carName",
"cost",
"fromDate",
"toDate",
"userEmailId"
})
public class VehicleBookingResponse {

@JsonProperty("returnCode")
private Integer returnCode;
@JsonProperty("returnMessage")
private String returnMessage;
@JsonProperty("invoiceId")
private Integer invoiceId;
@JsonProperty("carId")
private String carId;
@JsonProperty("carName")
private String carName;
@JsonProperty("cost")
private Double cost;
@JsonProperty("fromDate")
private String fromDate;
@JsonProperty("toDate")
private String toDate;
@JsonProperty("userEmailId")
private String userEmailId;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("returnCode")
public Integer getReturnCode() {
return returnCode;
}

@JsonProperty("returnCode")
public void setReturnCode(Integer returnCode) {
this.returnCode = returnCode;
}

@JsonProperty("returnMessage")
public String getReturnMessage() {
return returnMessage;
}

@JsonProperty("returnMessage")
public void setReturnMessage(String returnMessage) {
this.returnMessage = returnMessage;
}


@JsonProperty("carName")
public String getCarName() {
	return carName;
}

@JsonProperty("carName")
public void setCarName(String carName) {
	this.carName = carName;
}

@JsonProperty("invoiceId")
public Integer getInvoiceId() {
return invoiceId;
}

@JsonProperty("invoiceId")
public void setInvoiceId(Integer invoiceId) {
this.invoiceId = invoiceId;
}

@JsonProperty("carId")
public String getCarId() {
return carId;
}

@JsonProperty("carId")
public void setCarId(String carId) {
this.carId = carId;
}

@JsonProperty("cost")
public Double getCost() {
return cost;
}

@JsonProperty("cost")
public void setCost(Double cost) {
this.cost = cost;
}

@JsonProperty("fromDate")
public String getFromDate() {
return fromDate;
}

@JsonProperty("fromDate")
public void setFromDate(String fromDate) {
this.fromDate = fromDate;
}

@JsonProperty("toDate")
public String getToDate() {
return toDate;
}

@JsonProperty("toDate")
public void setToDate(String toDate) {
this.toDate = toDate;
}

@JsonProperty("userEmailId")
public String getUserEmailId() {
return userEmailId;
}

@JsonProperty("userEmailId")
public void setUserEmailId(String userEmailId) {
this.userEmailId = userEmailId;
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