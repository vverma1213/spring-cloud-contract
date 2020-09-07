package com.retailbank.jmscreditcardervice.Controller;

import com.retailbank.jmscreditcardervice.servie.JMSCreditCheckService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JMSCreditCardApplicationController {
    private final JMSCreditCheckService creditCheckService;

    public JMSCreditCardApplicationController(JMSCreditCheckService creditCheckService) {
        this.creditCheckService = creditCheckService;
    }

    @PostMapping(value = "/credit-card-applications", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApplyForCreditCardResponse applyForCreditCard(@RequestBody final ApplyForCreditCardRequest applyForCreditCardRequest) {
        return creditCheckService.doCheckForCitizen(applyForCreditCardRequest);
    }
}
