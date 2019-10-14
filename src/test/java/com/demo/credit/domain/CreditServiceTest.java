package com.demo.credit.domain;

import com.demo.credit.request.CreditRequest;
import com.demo.credit.response.CreditApplyResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreditServiceTest {

    @InjectMocks
    private CreditService creditService;

    @Mock
    private ScoreService scoreService;

    @Spy
    private LimitCalculateFactory limitCalculateFactory = new LimitCalculateFactory();

    @Mock
    private CreditRepository creditRepository;

    @Captor
    private ArgumentCaptor<Credit> creditArgumentCaptor;

    @Test
    public void should_reject_credit_request_when_score_less_than_500() {
        //Given
        when(scoreService.creditScore()).thenReturn(100);

        //When
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setMonthlyIncome(BigDecimal.TEN);
        CreditApplyResponse creditResponse = creditService.approveCreditRequest(creditRequest);

        assertThat(creditResponse.getRequestResult()).isEqualTo(CreditApplyRequest.REJECTED);
        assertThat(creditResponse.getCreditLimit()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    public void should_approve_credit_request_when_score_between_500_and_1000_and_monthly_income_less_than_500() {
        //Given
        int score = 600;
        BigDecimal monthlyIncome = BigDecimal.valueOf(3000);
        when(scoreService.creditScore()).thenReturn(score);

        //When
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setIdentityNumber(123L);
        creditRequest.setMonthlyIncome(monthlyIncome);
        creditRequest.setName("John");
        creditRequest.setSurname("Doe");
        creditRequest.setPhoneNumber("1234");

        CreditApplyResponse creditResponse = creditService.approveCreditRequest(creditRequest);

        assertThat(creditResponse.getRequestResult()).isEqualTo(CreditApplyRequest.APPROVED);
        assertThat(creditResponse.getCreditLimit()).isEqualByComparingTo(BigDecimal.valueOf(10000));
        verify(creditRepository).save(creditArgumentCaptor.capture());
        Credit credit = creditArgumentCaptor.getValue();
        assertThat(credit.getIdentityNumber()).isEqualTo(123L);
        assertThat(credit.getName()).isEqualTo("John");
        assertThat(credit.getSurname()).isEqualTo("Doe");
        assertThat(credit.getAmount()).isEqualTo(BigDecimal.valueOf(10000));
        assertThat(credit.getPhoneNumber()).isEqualTo("1234");
    }

    @Test
    public void should_approve_credit_request_when_score_between_500_and_1000_and_monthly_income_more_than_5000() {
        //Given
        int score = 600;
        BigDecimal monthlyIncome = BigDecimal.valueOf(7000);
        when(scoreService.creditScore()).thenReturn(score);

        //When
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setIdentityNumber(123L);
        creditRequest.setMonthlyIncome(monthlyIncome);
        creditRequest.setName("John");
        creditRequest.setSurname("Doe");
        creditRequest.setPhoneNumber("1234");

        CreditApplyResponse creditResponse = creditService.approveCreditRequest(creditRequest);

        assertThat(creditResponse.getRequestResult()).isEqualTo(CreditApplyRequest.APPROVED);
        assertThat(creditResponse.getCreditLimit()).isEqualByComparingTo(BigDecimal.valueOf(28000));
        verify(creditRepository).save(creditArgumentCaptor.capture());
        Credit credit = creditArgumentCaptor.getValue();
        assertThat(credit.getIdentityNumber()).isEqualTo(123L);
        assertThat(credit.getName()).isEqualTo("John");
        assertThat(credit.getSurname()).isEqualTo("Doe");
        assertThat(credit.getAmount()).isEqualTo(BigDecimal.valueOf(28000));
        assertThat(credit.getPhoneNumber()).isEqualTo("1234");
    }

    @Test
    public void should_approve_credit_request_when_score_between_more_1000() {
        //Given
        int score = 1100;
        BigDecimal monthlyIncome = BigDecimal.valueOf(3000);
        when(scoreService.creditScore()).thenReturn(score);

        //When
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setIdentityNumber(123L);
        creditRequest.setMonthlyIncome(monthlyIncome);
        creditRequest.setName("John");
        creditRequest.setSurname("Doe");
        creditRequest.setPhoneNumber("1234");

        CreditApplyResponse creditResponse = creditService.approveCreditRequest(creditRequest);

        assertThat(creditResponse.getRequestResult()).isEqualTo(CreditApplyRequest.APPROVED);
        assertThat(creditResponse.getCreditLimit()).isEqualByComparingTo(BigDecimal.valueOf(12000));
        verify(creditRepository).save(creditArgumentCaptor.capture());
        Credit credit = creditArgumentCaptor.getValue();
        assertThat(credit.getIdentityNumber()).isEqualTo(123L);
        assertThat(credit.getName()).isEqualTo("John");
        assertThat(credit.getSurname()).isEqualTo("Doe");
        assertThat(credit.getAmount()).isEqualTo(BigDecimal.valueOf(12000));
        assertThat(credit.getPhoneNumber()).isEqualTo("1234");
    }
}