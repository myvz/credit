package com.demo.credit.domain;

import com.demo.credit.response.CreditApplyResponse;

import java.math.BigDecimal;

public class HighIncomeLimitCalculateImpl implements LimitCalculate {

    private static final BigDecimal MULTIPLICAND = BigDecimal.valueOf(4);

    @Override
    public CreditApplyResponse calcLimit(BigDecimal monthlyIncome) {
        BigDecimal limitAmount = monthlyIncome.multiply(MULTIPLICAND);
        return new CreditApplyResponse(CreditApplyRequest.APPROVED, limitAmount);
    }
}
