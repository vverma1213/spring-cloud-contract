package com.retailbank.jmscreditcardervice.servie;

import com.retailbank.jmscreditcardervice.Controller.ApplyForCreditCardRequest;
import com.retailbank.jmscreditcardervice.Controller.ApplyForCreditCardResponse;
import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckResponse;
import com.retailbank.jmscreditcardervice.gateway.JMSCreditCardGateway;
import org.springframework.stereotype.Component;

@Component
public class JMSCreditCheckService {
    private final JMSCreditCardGateway creditCheckGateway;

    public JMSCreditCheckService(JMSCreditCardGateway creditCheckGateway) {
        this.creditCheckGateway = creditCheckGateway;
    }

    public ApplyForCreditCardResponse doCheckForCitizen(ApplyForCreditCardRequest applyForCreditCardRequest) {

        final JMSCreditCheckResponse creditCheckResponse = creditCheckGateway.doCreditCheckForCitizen(applyForCreditCardRequest.getCitizenNumber());

        final JMSCreditCheckResponse.Score score = creditCheckResponse.getScore();
        final String uuid = creditCheckResponse.getUuid();

        if (applyForCreditCardRequest.getCardType() == ApplyForCreditCardRequest.CardType.GOLD) {
            if (score == JMSCreditCheckResponse.Score.HIGH) {
                return new ApplyForCreditCardResponse(ApplyForCreditCardResponse.Status.GRANTED, uuid);
            } else if(score == JMSCreditCheckResponse.Score.LOW) {
                return new ApplyForCreditCardResponse(ApplyForCreditCardResponse.Status.DENIED, uuid);
            }
        }

        throw new RuntimeException("Card and score not yet implemented");
    }

}
