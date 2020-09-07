package com.retailbank.jmscreditcheckservice;

import com.retailbank.jmscreditcheckservice.message.JMSCreditScoreProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMessageVerifier
public abstract class BaseTestForJMS {
    @Autowired JmsMessageVerifier messaging;
    @Autowired JMSCreditScoreProducer producer;

    //@Autowired
    //private JMSCreditCheckService creditCheckService;

    public void scoreOfHigh() {
        final String uuid = "e2a8b899-6b62-4010-81f1-9faed24fed2b";
        producer.publishScore(new JMSCreditCheckResponse(uuid, JMSCreditCheckResponse.Score.HIGH));
    }

//    @PostConstruct
//    public void postConstruct() {
//        Mockito.when(creditCheckService.doCreditCheck(1234))
//                .thenReturn(new JMSCreditCheckResponse("e2a8b899-6b62-4010-81f1-9faed24fed2b", JMSCreditCheckResponse.Score.HIGH));
//    }
//
//    @Configuration
//    public static class TestConfiguration {
//
//        @MockBean
//        private JMSCreditCheckService creditCheckService;
//    }
}
