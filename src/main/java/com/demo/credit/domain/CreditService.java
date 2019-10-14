package com.demo.credit.domain;

import com.demo.credit.request.CreditRequest;
import com.demo.credit.response.CreditApplyResponse;
import org.springframework.stereotype.Service;

@Service
public class CreditService {

    private ScoreService scoreService;

    private CreditRepository creditRepository;

    private LimitCalculateFactory limitCalculateFactory;

    public CreditService(ScoreService scoreService,
                         CreditRepository creditRepository,
                         LimitCalculateFactory limitCalculateFactory) {
        this.scoreService = scoreService;
        this.creditRepository = creditRepository;
        this.limitCalculateFactory = limitCalculateFactory;
    }

    public CreditApplyResponse approveCreditRequest(CreditRequest creditRequest) {
        Integer score = scoreService.creditScore();
        CreditApplyResponse creditResponse = limitCalculateFactory
                .createLimitCalculate(score, creditRequest.getMonthlyIncome())
                .calcLimit(creditRequest.getMonthlyIncome());
        if (creditResponse.getRequestResult().equals(CreditApplyRequest.APPROVED)) {
            createCredit(creditRequest, creditResponse);
        }
        return creditResponse;
    }

    private void createCredit(CreditRequest creditRequest, CreditApplyResponse creditResponse) {
        Credit credit = new Credit();
        credit.setIdentityNumber(creditRequest.getIdentityNumber());
        credit.setName(creditRequest.getName());
        credit.setSurname(creditRequest.getSurname());
        credit.setPhoneNumber(creditRequest.getPhoneNumber());
        credit.setAmount(creditResponse.getCreditLimit());
        creditRepository.save(credit);
    }
}
