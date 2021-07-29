package com.accenture.assessment.service;

import com.accenture.assessment.model.PaymentDue;
import com.accenture.assessment.utility.CoffeeAssessmentJsonReader;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author Shashi Kumari
 *
 * Coffee Assessment Service class
 */
@Slf4j
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

        HashSet<String> usersKeySet = new HashSet<String>(userOrderDetails.keySet());
        usersKeySet.addAll(userPaymentDetails.keySet());

        for (String user: usersKeySet ) {
            double amountTobePaid =0 ,amountPaid =0 ,amountDue=0;

            if(userPaymentDetails.containsKey(user)) {
                amountPaid = userPaymentDetails.get(user);
            }
            if(userOrderDetails.containsKey(user)) {
                amountTobePaid = userOrderDetails.get(user);
                amountDue = amountTobePaid - amountPaid;
            }else{
                log.info("No orders, but Payment done by User : " + user + " - No Due Amount");
                amountDue = 0;
            }

            PaymentDue paymentDue = new PaymentDue(user, amountPaid, amountDue);
            paymentDueList.add(paymentDue);
        }
        return paymentDueList;
    }


}
