package com.accenture.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @author Shashi Kumari
 *
 * PaymentDue Model
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDue {

    private String user;
    private double amountPaid;
    private double amountDue;
}
