package com.retailbank.jmscreditcardervice;

import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckResponse;
import com.retailbank.jmscreditcardervice.listener.JMSCreditScoreListener;
import com.retailbank.jmscreditcardervice.producer.JMSCreditScoreProducer;
import com.retailbank.jmscreditcardervice.repository.JMSCreditScoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = "com.retailbank:jmscreditcheckservice:+:stubs", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class JMSConsumerIntegrationTest {
    @Autowired
    private StubTrigger stubTrigger;

    @Autowired
    private JMSCreditScoreRepository creditScoreRepository;

    @Autowired
    private JMSCreditScoreProducer creditScoreProducer;

    @Autowired
    private JMSCreditScoreListener listener;

    @Test
    public void shouldStoreResultsOfACreditCheck() {
        // Given
        final UUID uuid = UUID.fromString("e2a8b899-6b62-4010-81f1-9faed24fed2b");

        // When
        stubTrigger.trigger("score_of_high");

        // Then
        final JMSCreditCheckResponse.Score score = creditScoreRepository.getScore(uuid);

        Assertions.assertThat(score).isEqualTo(JMSCreditCheckResponse.Score.HIGH);
    }
}
