
package com.gsnimbus.api.dto.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingApiDto {
    private String lat;
    private String lon;
    @JsonProperty("display_name")
    private String displayName;
}