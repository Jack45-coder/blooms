package in.codingage.blooms.controller;

import in.codingage.blooms.exception.ApplicationException;
import in.codingage.blooms.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // you can add methods here to handle specific exceptions and return appropriate responses
    @ExceptionHandler(ApplicationException.class)
    public ApiResponse<Void> handleException(ApplicationException applicationException){
        return new ApiResponse<>(false, applicationException.getMessage(), null);
    }

    @ExceptionHandler(RuntimeException .class)
    public ApiResponse<Void> handleRuntimeException(RuntimeException runtimeException){
        return new ApiResponse<>(false, runtimeException.getMessage(), null);
    }
}
