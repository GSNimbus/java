package com.gsnimbus.api.dto.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {
    @JsonProperty("house_number")
    private String houseNumber;
    private String road;
    private String suburb;
    private String city;
    private String state;
    private String postcode;
    private String country;
}
