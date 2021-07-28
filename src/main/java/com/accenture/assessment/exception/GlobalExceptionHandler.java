package com.accenture.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Global exception handler class
 *
 */
@RestControllerAdvice(basePackages = "com.accenture.assessment.controller")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * @param ex Generic Exception
     * @param request Web request Exception
     * @return ErrorResponse
     * <p>
     * This method is to handle all other internal exceptions
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleExceptions(Exception ex, WebRequest request) {

        String errorMessage = ex.getLocalizedMessage();
        List<String> errorDesc = new ArrayList<>();
        errorDesc.add(errorMessage);

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error", errorDesc, request.getDescription(false), new Date());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
