package com.retailbank.jmscreditcheckservice;

import com.retailbank.jmscreditcheckservice.message.JMSCreditScoreProducer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

import static org.mockito.Mockito.*;

public class ProducerTest {
    private JmsTemplate jmsTemplate;

    @Before
    public void setUp() throws Exception {
        jmsTemplate = mock(JmsTemplate.class);
    }

    @Test
    public void shouldSendMessageWithTraindId() {
        JMSCreditScoreProducer producer = new JMSCreditScoreProducer(jmsTemplate);
        String uuid = "e2a8b899-6b62-4010-81f1-9faed24fed2b";

        producer.publishScore(new JMSCreditCheckResponse(uuid, JMSCreditCheckResponse.Score.HIGH));

        verify(jmsTemplate,times(1)).convertAndSend(anyString(),any(Object.class),any(MessagePostProcessor.class));
    }
}
