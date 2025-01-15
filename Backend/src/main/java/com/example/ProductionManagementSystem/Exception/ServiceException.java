package com.example.ProductionManagementSystem.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class ServiceException extends Exception {
    private final HttpStatusCode status;
    private final String code;

    public ServiceException(ErrorResponse error) {
        this(error.getStatus(), error.getCode(), error.getMessage());
    }

    public ServiceException(HttpStatusCode status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }
}
