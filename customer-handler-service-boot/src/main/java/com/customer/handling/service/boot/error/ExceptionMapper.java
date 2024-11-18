package com.customer.handling.service.boot.error;

import com.customer.handling.service.api.model.Error;
import com.customer.handling.service.api.model.ErrorResponse;
import com.customer.handling.service.exception.ItemInDbNotFoundException;
import com.customer.handling.service.exception.RequestNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionMapper {

    private final Clock clock = Clock.system(ZoneId.systemDefault());
    @ExceptionHandler(RequestNotValidException.class)
    public ResponseEntity<ErrorResponse> handleRequestNotValidException(
            HttpServletRequest httpServletRequest,
            RequestNotValidException requestNotValidException
    ) {
        log.error(requestNotValidException.getMessage());

        return createResponseEntity(
                HttpStatus.BAD_REQUEST,
                httpServletRequest.getRequestURI(),
                "CODE!",
                requestNotValidException.getMessage()
        );
    }

    @ExceptionHandler(ItemInDbNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleItemInDbNotFoundException(
            HttpServletRequest httpServletRequest,
            ItemInDbNotFoundException itemInDbNotFoundException
    ) {
        log.error(itemInDbNotFoundException.getMessage());

        return createResponseEntity(
                HttpStatus.BAD_REQUEST,
                httpServletRequest.getRequestURI(),
                "CODE2!",
                itemInDbNotFoundException.getMessage()
        );
    }

    private ResponseEntity<ErrorResponse> createResponseEntity(
            HttpStatus httpStatus,
            String requestUri,
            String code,
            String detail) {
        return ResponseEntity
                .status(httpStatus)
                .body(new ErrorResponse(
                        OffsetDateTime.ofInstant(Instant.now(clock), ZoneId.systemDefault()),
                        requestUri,
                        detail,
                        List.of(new Error(code,detail))
                ));
    }
}