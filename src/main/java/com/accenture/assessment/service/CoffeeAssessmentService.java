package com.accenture.assessment.service;

import com.accenture.assessment.model.PaymentDue;
import com.accenture.assessment.utility.CoffeeAssessmentJsonReader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Shashi Kumari
 *
 * Coffee Assessment Service class
 */
@Service
public class CoffeeAssessmentService {

    /**
     *
     * @return list of Payment due details for Users
     */
    public List<PaymentDue> getPaymentDueDetails(){
        List<PaymentDue> paymentDueList = new ArrayList<>();
        Map<String, Double> userOrderDetails = CoffeeAssessmentJsonReader.getUserOrderDetails();
        Map<String, Double> userPaymentDetails = CoffeeAssessmentJsonReader.getUserPaymentDetails();

        for (String user: userOrderDetails.keySet() ) {

            double amountTobePaid = userOrderDetails.get(user);
            double amountPaid = userPaymentDetails.get(user);
            double amountDue = amountTobePaid - amountPaid;

            PaymentDue paymentDue = new PaymentDue(user, amountPaid, amountDue);
            paymentDueList.add(paymentDue);
        }
        return paymentDueList;
    }


}
