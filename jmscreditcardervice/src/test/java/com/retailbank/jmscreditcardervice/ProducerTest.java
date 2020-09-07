package com.retailbank.jmscreditcardervice;

import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckRequest;
import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckResponse;
import com.retailbank.jmscreditcardervice.producer.JMSCreditScoreProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ProducerTest {

    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    public void shouldSendMessageContainingTheAvailabilityResponse() {
        String uuid = "e2a8b899-6b62-4010-81f1-9faed24fed2b";
        JMSCreditScoreProducer producer = new JMSCreditScoreProducer(jmsTemplate);
        JMSCreditCheckResponse response = new JMSCreditCheckResponse(JMSCreditCheckResponse.Score.HIGH,uuid);

        producer.requestScore(new JMSCreditCheckRequest(1234));

        verify(jmsTemplate, times(1)).convertAndSend(eq("credit_card_applications"), eq(response), any(MessagePostProcessor.class));
    }
}
