package com.accenture.assessment.controller;


import com.accenture.assessment.model.PaymentDue;
import com.accenture.assessment.service.CoffeeAssessmentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CoffeeAssessmentRestController.class)
public class CoffeeAssessmentRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CoffeeAssessmentService coffeeAssessmentService;

    private static List<PaymentDue> paymentDueList = new ArrayList<>();

    @BeforeAll
    public static void initMockInput() {

        PaymentDue PaymentDueForUser1 = new PaymentDue("nick", 100.0, 50.0);
        PaymentDue PaymentDueForUser2 = new PaymentDue("joey", 80.0, 20.0);

        paymentDueList.add(PaymentDueForUser1);
        paymentDueList.add(PaymentDueForUser2);
    }

    @Test
    public void getPaymentDueDetailsTest() throws Exception {

        Mockito.when(coffeeAssessmentService.getPaymentDueDetails()).thenReturn(paymentDueList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coffee-assessment/api/v1/payment-details")
                .accept(MediaType.APPLICATION_JSON);

        ResultActions resultActions = mvc.perform(requestBuilder);
        Mockito.verify(coffeeAssessmentService,Mockito.times(1)).getPaymentDueDetails();
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].amountDue", is(50.0)));
    }
}
