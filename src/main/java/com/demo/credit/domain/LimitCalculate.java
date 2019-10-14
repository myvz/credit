package com.demo.credit.domain;

import com.demo.credit.response.CreditApplyResponse;

import java.math.BigDecimal;

public interface LimitCalculate {
    CreditApplyResponse calcLimit(BigDecimal monthlyIncome);
}
