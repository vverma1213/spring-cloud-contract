package com.retailbank.jmscreditcardervice.producer;

import com.retailbank.jmscreditcardervice.converter.ReplyToProcessor;
import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckRequest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JMSCreditScoreProducer {
    private JmsTemplate jmsTemplate;
    public JMSCreditScoreProducer(JmsTemplate jmsTemplate) { this.jmsTemplate = jmsTemplate; }

    public void requestScore(JMSCreditCheckRequest creditCheckRequest) {
        jmsTemplate.convertAndSend("credit_card_applications",creditCheckRequest,new ReplyToProcessor());
    }
}
