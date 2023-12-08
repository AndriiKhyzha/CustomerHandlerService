package com.customer.handling.service.api;

import lombok.Builder;
@Builder
public record CustomerData(
        Integer dbId,
        String name,
        AddressData address) {
}