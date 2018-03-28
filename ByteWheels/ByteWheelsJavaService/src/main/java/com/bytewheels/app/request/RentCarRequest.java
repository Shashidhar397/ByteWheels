package com.bytewheels.app.request;

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
"rentingDate",
"category",
"costFrom",
"costTo"
})
public class RentCarRequest {

@JsonProperty("rentingDate")
private String rentingDate;
@JsonProperty("category")
private String category;
@JsonProperty("costFrom")
private Integer costFrom;
@JsonProperty("costTo")
private Integer costTo;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("rentingDate")
public String getRentingDate() {
return rentingDate;
}

@JsonProperty("rentingDate")
public void setRentingDate(String rentingDate) {
this.rentingDate = rentingDate;
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
public Integer getCostFrom() {
return costFrom;
}

@JsonProperty("costFrom")
public void setCostFrom(Integer costFrom) {
this.costFrom = costFrom;
}

@JsonProperty("costTo")
public Integer getCostTo() {
return costTo;
}

@JsonProperty("costTo")
public void setCostTo(Integer costTo) {
this.costTo = costTo;
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
