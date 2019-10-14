package com.demo.credit.controller;

import com.demo.credit.domain.CreditService;
import com.demo.credit.request.CreditRequest;
import com.demo.credit.response.CreditApplyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/credit")
public class CreditController {

    private CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditApplyResponse credit(@Valid @RequestBody CreditRequest creditRequest) {
        return creditService.approveCreditRequest(creditRequest);
    }
}
