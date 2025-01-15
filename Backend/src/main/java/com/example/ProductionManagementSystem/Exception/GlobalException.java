package com.example.ProductionManagementSystem.Exception;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.lang.reflect.UndeclaredThrowableException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = {ServiceException.class})
    public Object serviceException(ServiceException ex){
        return new ResponseEntity<Object>(new ErrorResponse(ex.getStatus(), ex.getCode(), ex.getMessage()),
                ex.getStatus() == null ? HttpStatus.BAD_REQUEST : ex.getStatus());
    }

    @ExceptionHandler(value = {UnexpectedTypeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse unexpectedTypeException(UnexpectedTypeException ex){
        return ErrorResponse.BAD_REQUEST;
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse constraintViolationException(ConstraintViolationException ex){
        return ErrorResponse.BAD_REQUEST;
    }

    @ExceptionHandler(value = {NoResourceFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse noResourceFoundException(Exception ex){
        return ErrorResponse.NOT_FOUND;
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse methodNotSupportedException(Exception ex){
        return ErrorResponse.METHOD_NOT_ALLOWED;
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse methodValidationException(MethodArgumentNotValidException ex) {
        return ErrorResponse.VALIDATION_ERROR;
    }

    @ExceptionHandler(value = {UndeclaredThrowableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse UndeclaredThrowableException(UndeclaredThrowableException ex) {
        return ErrorResponse.SERVICE_ERROR;
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleExpiredJwtException(ExpiredJwtException ex) {
        return ErrorResponse.JWT_EXPIRED;
    }

//    @ExceptionHandler(value = {InvalidDataAccessApiUsageException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse invalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex) {
//        return new ErrorResponse(HttpStatus.BAD_REQUEST, "S001", "Invalid data access: " + ex.getMessage());
//    }
}
