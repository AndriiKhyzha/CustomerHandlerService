package com.customer.handling.service.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record AddressData (
        @JsonProperty("dbId")
        Integer dbId,

        @JsonProperty("country")
        String country,

        @JsonProperty("city")
        String city,

        @JsonProperty("street")
        String street,

        @JsonProperty("number")
        String number) {
}
