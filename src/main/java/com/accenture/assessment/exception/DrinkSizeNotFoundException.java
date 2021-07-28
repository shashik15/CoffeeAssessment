package com.accenture.assessment.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Shashi Kumari
 *
 * Custom Exception for Drink size not found
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class DrinkSizeNotFoundException extends RuntimeException {

    public DrinkSizeNotFoundException(String message)
    {
        super(message);
    }
}
