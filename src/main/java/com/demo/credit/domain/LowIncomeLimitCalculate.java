package com.demo.credit.domain;

import com.demo.credit.response.CreditApplyResponse;

import java.math.BigDecimal;

public class LowIncomeLimitCalculate implements LimitCalculate {

    private static final BigDecimal CREDIT_LIMIT = BigDecimal.valueOf(10000);

    @Override
    public CreditApplyResponse calcLimit(BigDecimal monthlyIncome) {
        return new CreditApplyResponse(CreditApplyRequest.APPROVED, CREDIT_LIMIT);
    }
}
