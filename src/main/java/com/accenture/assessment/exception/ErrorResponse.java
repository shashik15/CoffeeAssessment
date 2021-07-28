package com.accenture.assessment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * @author Shashi Kumari
 *
 *  Custom Error Response model
 *
 */
@AllArgsConstructor
@Getter
public class ErrorResponse {

    private final int code;
    private final String message;
    private final List<String> details;
    private final String path;
    private final Date timestamp;

}
