package com.customer.handling.service.boot.error;

import com.customer.handling.service.api.error.Error;
import com.customer.handling.service.api.error.ErrorResponse;
import com.customer.handling.service.exception.ItemInDbNotFoundException;
import com.customer.handling.service.exception.RequestNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionMapper {

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
                .body(
                        ErrorResponse.builder()
                                .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                                .path(requestUri)
                                .errors(List.of(
                                        Error.builder()
                                                .code(code)
                                                .detail(detail)
                                                .build()
                                ))
                                .build()
                );
    }
}