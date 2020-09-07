package com.retailbank.jmscreditcardervice.domain;

public class JMSCreditCheckResponse {
    private Score score;
    private String uuid;

    public JMSCreditCheckResponse(Score score,String uuid) {
        this.score = score;
        this.uuid = uuid;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public enum Score{
        HIGH,LOW
    }
}
