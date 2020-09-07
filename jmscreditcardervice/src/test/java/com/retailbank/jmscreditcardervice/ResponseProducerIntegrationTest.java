package com.retailbank.jmscreditcardervice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckRequest;
import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckResponse;
import com.retailbank.jmscreditcardervice.producer.JMSCreditScoreProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResponseProducerIntegrationTest {

    @Autowired
    private JMSCreditScoreProducer producer;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldSendMessageWithUuId() throws JMSException, IOException {
        String uuid = "e2a8b899-6b62-4010-81f1-9faed24fed2b";
        producer.requestScore(new JMSCreditCheckRequest(1234));

        jmsTemplate.setReceiveTimeout(1000);
        Message message = jmsTemplate.receive("credit_card_applications");
        TextMessage textMessage = (TextMessage) message;

        JMSCreditCheckResponse response = mapper.readValue(textMessage.getText(), JMSCreditCheckResponse.class);
        assertThat(response.getUuid()).isEqualTo(uuid);
        assertThat(response.getScore()).isEqualTo(JMSCreditCheckResponse.Score.HIGH);
    }
}