package com.retailbank.jmscreditcheckservice.message;

import com.retailbank.jmscreditcheckservice.JMSCreditCheckRequest;
import com.retailbank.jmscreditcheckservice.JMSCreditCheckResponse;
import com.retailbank.jmscreditcheckservice.JMSCreditCheckService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSCreditScoreConsumer {

private final JMSCreditScoreProducer jmsCreditScoreProducer;
private final JMSCreditCheckService jmsCreditCheckService;

    public JMSCreditScoreConsumer(JMSCreditScoreProducer jmsCreditScoreProducer, JMSCreditCheckService jmsCreditCheckService) {
        this.jmsCreditScoreProducer = jmsCreditScoreProducer;
        this.jmsCreditCheckService = jmsCreditCheckService;
    }

    @JmsListener(destination = "credit_card_applications")
    public void consume(JMSCreditCheckRequest jmsCreditCheckRequest){
        JMSCreditCheckResponse jmsCreditCheckResponse= jmsCreditCheckService.doCreditCheck(jmsCreditCheckRequest.getCitizenNumber());
        jmsCreditScoreProducer.publishScore(jmsCreditCheckResponse);
    }
}
