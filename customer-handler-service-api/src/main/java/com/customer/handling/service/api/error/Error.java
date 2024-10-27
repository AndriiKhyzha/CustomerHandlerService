package com.customer.handling.service.api.error;

import lombok.Builder;

@Builder
public record Error(
        String code,
        String detail) {
}
