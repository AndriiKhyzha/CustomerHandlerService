package com.customer.handling.service.api.error;
import java.time.ZonedDateTime;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponse(
        ZonedDateTime timestamp,
        String path,
        String detail,
        List<Error> errors) {
}
