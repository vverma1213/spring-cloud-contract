package com.retailbank.creditcardservice.service;

import com.retailbank.creditcardservice.controller.ApplyForCreditCardRequest;
import com.retailbank.creditcardservice.controller.ApplyForCreditCardResponse;
import com.retailbank.creditcardservice.gateway.CreditCheckGateway;
import com.retailbank.creditcardservice.gateway.CreditCheckResponse;
import org.springframework.stereotype.Component;

import static com.retailbank.creditcardservice.controller.ApplyForCreditCardRequest.CardType.GOLD;
import static com.retailbank.creditcardservice.controller.ApplyForCreditCardResponse.Status.DENIED;
import static com.retailbank.creditcardservice.controller.ApplyForCreditCardResponse.Status.GRANTED;
import static com.retailbank.creditcardservice.gateway.CreditCheckResponse.Score.HIGH;
import static com.retailbank.creditcardservice.gateway.CreditCheckResponse.Score.LOW;

@Component
public class CreditCheckService {

    private final CreditCheckGateway creditCheckGateway;

    public CreditCheckService(CreditCheckGateway creditCheckGateway) {
        this.creditCheckGateway = creditCheckGateway;
    }

    public ApplyForCreditCardResponse doCheckForCitizen(ApplyForCreditCardRequest applyForCreditCardRequest) {

        final CreditCheckResponse creditCheckResponse = creditCheckGateway.doCreditCheckForCitizen(applyForCreditCardRequest.getCitizenNumber());

        final CreditCheckResponse.Score score = creditCheckResponse.getScore();
        final String uuid = creditCheckResponse.getUuid();

        if (applyForCreditCardRequest.getCardType() == GOLD) {
            if (score == HIGH) {
                return new ApplyForCreditCardResponse(GRANTED, uuid);
            } else if(score == LOW) {
                return new ApplyForCreditCardResponse(DENIED, uuid);
            }
        }

        throw new RuntimeException("Card and score not yet implemented");
    }
}
