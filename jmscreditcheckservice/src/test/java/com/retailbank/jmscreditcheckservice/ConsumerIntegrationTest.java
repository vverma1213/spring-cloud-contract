package com.retailbank.jmscreditcheckservice;

import com.retailbank.jmscreditcheckservice.message.JMSCreditScoreConsumer;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConsumerIntegrationTest {


    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    JMSCreditScoreConsumer consumer;

    @MockBean
    private PrinterService service;

    @Test
    public void shouldParseAvailabilityResponse() throws InterruptedException {
        JMSCreditCheckResponse response = sendTestAvailabilityResponse();
        verify(service, times(1)).printResult(eq(response));
    }

    private JMSCreditCheckResponse sendTestAvailabilityResponse() {
        JMSCreditCheckResponse response = new JMSCreditCheckResponse("e2a8b899-6b62-4010-81f1-9faed24fed2b", JMSCreditCheckResponse.Score.HIGH);
        //response.setUuid("e2a8b899-6b62-4010-81f1-9faed24fed2b");
        //response.setAvailableSeats(3);
        jmsTemplate.convertAndSend("credit_scores", response);
        return response;
    }
}
