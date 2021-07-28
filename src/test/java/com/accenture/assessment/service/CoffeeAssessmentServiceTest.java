package com.accenture.assessment.service;

import com.accenture.assessment.model.PaymentDue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CoffeeAssessmentServiceTest {

    @Mock
    private CoffeeAssessmentService service;

    @Test
    public void getPaymentsDueDetailsForAnUserTest()
    {
        List<PaymentDue> paymentDueDetails = service.getPaymentDueDetails();

        assertNotNull(paymentDueDetails);


    }
}
