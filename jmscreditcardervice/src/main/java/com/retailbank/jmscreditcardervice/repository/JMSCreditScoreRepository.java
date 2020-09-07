package com.retailbank.jmscreditcardervice.repository;

import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JMSCreditScoreRepository {

    private final Map<String, JMSCreditCheckResponse.Score> creditScores = new HashMap<>();

    public JMSCreditCheckResponse.Score getScore(UUID uuid) {
        return creditScores.get(uuid.toString());
    }

    public void save(String uuid, JMSCreditCheckResponse.Score score) {
        creditScores.put(uuid, score);
    }
}
