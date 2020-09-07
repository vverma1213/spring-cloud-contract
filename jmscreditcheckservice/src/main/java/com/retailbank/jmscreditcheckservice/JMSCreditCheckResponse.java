package com.retailbank.jmscreditcheckservice;

public class JMSCreditCheckResponse {
    private String uuid;
    private Score score;

    public JMSCreditCheckResponse(String uuid, Score score) {
        this.uuid = uuid;
        this.score = score;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public enum Score{
        HIGH,LOW
    }
}
