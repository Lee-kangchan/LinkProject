package com.chan.link.global.handler.exception;

import com.chan.link.global.constant.ErrorCode;
import com.chan.link.global.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<ErrorResponse> handleDataException() {
        log.error("handleDataException throw Exception : {}", ErrorCode.DUPLICATE_RESOURCE);
        return ErrorResponse.toResponseEntity(ErrorCode.DUPLICATE_RESOURCE);
    }
}
