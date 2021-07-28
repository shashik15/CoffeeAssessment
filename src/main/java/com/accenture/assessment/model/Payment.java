package com.accenture.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Shashi Kumari
 *
 * Payment Model
 */
@Getter
@Setter
public class Payment {

    private String user;
    @JsonProperty("amount")
    private double amountPaid;
}
