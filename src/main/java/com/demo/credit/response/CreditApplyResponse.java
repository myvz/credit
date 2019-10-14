package com.demo.credit.response;

import com.demo.credit.domain.CreditApplyRequest;

import java.math.BigDecimal;

public class CreditApplyResponse {

    private CreditApplyRequest requestResult;

    private BigDecimal creditLimit;

    public CreditApplyResponse(CreditApplyRequest requestResult, BigDecimal creditLimit) {
        this.requestResult = requestResult;
        this.creditLimit = creditLimit;
    }

    public CreditApplyRequest getRequestResult() {
        return requestResult;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }
}
