package com.retailbank.jmscreditcheckservice.message;

import com.retailbank.jmscreditcheckservice.JMSCreditCheckResponse;
import com.retailbank.jmscreditcheckservice.ReplyToProcessor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JMSCreditScoreProducer {

    private JmsTemplate jmsTemplate;

    public JMSCreditScoreProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void publishScore(JMSCreditCheckResponse jmsCreditCheckResponse){
        jmsTemplate.convertAndSend("credit_scores",jmsCreditCheckResponse, new ReplyToProcessor());
    }
}
