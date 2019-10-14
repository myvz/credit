package com.demo.credit.controller;

import com.demo.credit.domain.CreditApplyRequest;
import com.demo.credit.domain.CreditService;
import com.demo.credit.request.CreditRequest;
import com.demo.credit.response.CreditApplyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CreditController.class)
public class CreditControllerTest {

    @MockBean
    private CreditService creditService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_response_with_201_when_credit_approved() throws Exception {

        when(creditService.approveCreditRequest(any())).thenReturn(new CreditApplyResponse(CreditApplyRequest.APPROVED, BigDecimal.TEN));

        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setIdentityNumber(123L);
        creditRequest.setMonthlyIncome(BigDecimal.TEN);
        creditRequest.setName("John");
        creditRequest.setSurname("Doe");
        creditRequest.setPhoneNumber("1234");
        this.mockMvc.perform(post("/credit")
                .content(objectMapper.writeValueAsString(creditRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.requestResult").value("APPROVED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creditLimit").value(BigDecimal.TEN));
    }
}