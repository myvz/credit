package com.demo.credit.request;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreditRequest {
    @NotNull
    private Long identityNumber;
    @NotNull
    private String name;
    @NotNull
    private String surname;

    @NotNull
    private BigDecimal monthlyIncome;

    @NotNull
    private String phoneNumber;

    public Long getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(Long identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
