package com.retailbank.jmscreditcardervice.listener;

import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckResponse;
import com.retailbank.jmscreditcardervice.repository.JMSCreditScoreRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSCreditScoreListener {

    private final JMSCreditScoreRepository creditScoreRepository;

    public JMSCreditScoreListener(JMSCreditScoreRepository creditScoreRepository) {
        this.creditScoreRepository = creditScoreRepository;
    }

    @JmsListener(destination = "credit_scores")
    public void receiveScore(JMSCreditCheckResponse creditCheckResponse) {
        creditScoreRepository.save(creditCheckResponse.getUuid(), creditCheckResponse.getScore());
    }
}
