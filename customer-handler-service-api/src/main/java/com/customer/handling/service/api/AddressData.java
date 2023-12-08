package com.customer.handling.service.api;

import lombok.Builder;

@Builder
public record AddressData (
        Integer dbId,
        String country,
        String city,
        String street,
        String number) {
}
