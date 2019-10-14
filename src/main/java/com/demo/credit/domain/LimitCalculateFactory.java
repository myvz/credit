package com.demo.credit.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LimitCalculateFactory {

    private static final int SCORE_LIMIT = 500;
    private static final BigDecimal MONTHLY_INCOME_LIMIT = BigDecimal.valueOf(5000);

    public LimitCalculate createLimitCalculate(int score, BigDecimal monthlyIncome) {
        if (score < SCORE_LIMIT) {
            return new LowScoringLimitCalculate();
        } else if (score < 1000 && monthlyIncome.compareTo(MONTHLY_INCOME_LIMIT) < 0) {
            return new LowIncomeLimitCalculate();
        }
        return new HighIncomeLimitCalculateImpl();
    }
}
