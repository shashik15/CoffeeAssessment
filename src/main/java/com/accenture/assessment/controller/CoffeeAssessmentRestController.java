package com.accenture.assessment.controller;

import com.accenture.assessment.model.PaymentDue;
import com.accenture.assessment.service.CoffeeAssessmentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Shashi Kumari
 *
 * Rest Controller for Coffe Assessment Application
 */
@RestController
@RequestMapping("/coffee-assessment/api/v1")
public class CoffeeAssessmentRestController {

    private final CoffeeAssessmentService coffeeAssessmentService;

    public CoffeeAssessmentRestController(CoffeeAssessmentService coffeeAssessmentService) {
        this.coffeeAssessmentService = coffeeAssessmentService;
    }

    /**
     *
     * @return the list of Payment due details for all the users
     */
    @GetMapping("/payment-details")
    public ResponseEntity<List<PaymentDue>> getPaymentDueDetails() {
        return new ResponseEntity<>(coffeeAssessmentService.getPaymentDueDetails(),
                new HttpHeaders(), HttpStatus.OK);
    }


}
