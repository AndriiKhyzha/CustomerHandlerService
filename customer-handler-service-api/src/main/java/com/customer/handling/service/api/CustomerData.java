package com.customer.handling.service.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public record CustomerData(
        @JsonProperty("dbId")
        Integer dbId,
        @JsonProperty("name")
        String name,
        @JsonProperty("address")
        AddressData address) {
}