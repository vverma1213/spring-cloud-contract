package com.retailbank.jmscreditcardervice.domain;

import java.util.UUID;

public class JMSCreditCheckRequest {
    private int citizenNumber;
    private String uuid = UUID.randomUUID().toString();

    public JMSCreditCheckRequest(int citizenNumber) {
        this.citizenNumber = citizenNumber;
    }

    public int getCitizenNumber() {
        return citizenNumber;
    }

    public void setCitizenNumber(int citizenNumber) {
        this.citizenNumber = citizenNumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
