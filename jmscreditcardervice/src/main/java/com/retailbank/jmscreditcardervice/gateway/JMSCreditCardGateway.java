package com.retailbank.jmscreditcardervice.gateway;

import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckRequest;
import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class JMSCreditCardGateway {

    private final RestTemplate restTemplate;
    private final String creditcheckserviceBaseUrl;


    public JMSCreditCardGateway(RestTemplate restTemplate, @Value("http://localhost:8080") String creditcheckserviceBaseUrl) {
        this.restTemplate = restTemplate;
        this.creditcheckserviceBaseUrl = creditcheckserviceBaseUrl;
    }

    public JMSCreditCheckResponse doCreditCheckForCitizen(int citizenNumber) {
        final String uri = UriComponentsBuilder.fromHttpUrl(creditcheckserviceBaseUrl)
                .path("credit-scores")
                .toUriString();

        final JMSCreditCheckRequest request = new JMSCreditCheckRequest(citizenNumber);
        final JMSCreditCheckResponse creditCheckResponse = restTemplate.postForObject(uri, request, JMSCreditCheckResponse.class);

        if (!creditCheckResponse.getUuid().equals(request.getUuid())) {
            throw new RuntimeException("If these don't match something horrible happens");
        }

        return creditCheckResponse;
    }
}
