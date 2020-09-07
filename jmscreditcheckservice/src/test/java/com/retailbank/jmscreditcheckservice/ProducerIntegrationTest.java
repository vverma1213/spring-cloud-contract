package com.retailbank.jmscreditcheckservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailbank.jmscreditcheckservice.message.JMSCreditScoreProducer;
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
public class ProducerIntegrationTest {
    @Autowired
    private JMSCreditScoreProducer producer;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    //new JMSCreditCheckResponse("e2a8b899-6b62-4010-81f1-9faed24fed2b", JMSCreditCheckResponse.Score.HIGH)
    @Test
    public void shouldSendMessageWithTrainId() throws JMSException, IOException {
        producer.publishScore(new JMSCreditCheckResponse("e2a8b899-6b62-4010-81f1-9faed24fed2b", JMSCreditCheckResponse.Score.HIGH));

        jmsTemplate.setReceiveTimeout(1000);
        Message message = jmsTemplate.receive("credit_card_applications");
        TextMessage textMessage = (TextMessage) message;

        JMSCreditCheckRequest request = objectMapper.readValue(textMessage.getText(), JMSCreditCheckRequest.class);
        assertThat(request.getUuid()).isEqualTo("e2a8b899-6b62-4010-81f1-9faed24fed2b");
    }

}
