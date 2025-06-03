package com.gsnimbus.api.dto.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReverseGeocodingApiDto {
    private AddressDto address;
}
