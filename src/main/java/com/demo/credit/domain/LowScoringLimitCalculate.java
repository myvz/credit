package com.demo.credit.domain;

import com.demo.credit.response.CreditApplyResponse;

import java.math.BigDecimal;

public class LowScoringLimitCalculate implements LimitCalculate {

    @Override
    public CreditApplyResponse calcLimit(BigDecimal monthlyIncome) {
        return new CreditApplyResponse(CreditApplyRequest.REJECTED, BigDecimal.ZERO);
    }
}
