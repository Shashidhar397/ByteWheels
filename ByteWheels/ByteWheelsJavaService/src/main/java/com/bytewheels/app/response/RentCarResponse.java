package com.bytewheels.app.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"availableDate",
"vehicles"
})
public class RentCarResponse {

@JsonProperty("availableDate")
private String availableDate;
@JsonProperty("vehicles")
private List<Vehicle> vehicles = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("availableDate")
public String getAvailableDate() {
return availableDate;
}

@JsonProperty("availableDate")
public void setAvailableDate(String availableDate) {
this.availableDate = availableDate;
}

@JsonProperty("vehicles")
public List<Vehicle> getVehicles() {
return vehicles;
}

@JsonProperty("vehicles")
public void setVehicles(List<Vehicle> vehicles) {
this.vehicles = vehicles;
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