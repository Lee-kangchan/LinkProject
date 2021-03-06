package com.chan.link.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.nio.file.attribute.UserPrincipalNotFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<ErrorResponse> handleDataException() {
        log.error("handleDataException throw Exception : {}", ErrorCode.DUPLICATE_RESOURCE);
        return ErrorResponse.toResponseEntity(ErrorCode.DUPLICATE_RESOURCE);
    }

    @ExceptionHandler(RuntimeException.class)
    protected  ResponseEntity<ErrorResponse> handleRuntimeException(){
        log.error("runtimeException : {}", ErrorCode.USER_ALREADY_SIGN);

        return ErrorResponse.toResponseEntity(ErrorCode.USER_ALREADY_SIGN);
    }

    @ExceptionHandler(UserPrincipalNotFoundException.class)
    protected  ResponseEntity<ErrorResponse> handleUserPrincipalNotFoundException(){
        log.error("runtimeException : {}", ErrorCode.USER_NOT_FOUND);

        return ErrorResponse.toResponseEntity(ErrorCode.USER_NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);

        return ErrorResponse.toResponseEntity(ErrorCode.USER_NOT_FOUND);
    }

    @ExceptionHandler(Exception.class) // 알 수 없는 예외 핸들링
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleException", e);

        return ErrorResponse.toResponseEntity(ErrorCode.SERVER_ERROR);
    }
}
