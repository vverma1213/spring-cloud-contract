package com.retailbank.jmscreditcheckservice;

import org.springframework.stereotype.Component;

@Component
public class PrinterService {

    public void printResult(final JMSCreditCheckResponse response) {
        System.out.printf("Availability Report:UUID %s, available Score %s", response.getUuid(), response.getScore());
    }
}
