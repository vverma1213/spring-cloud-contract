package com.retailbank.jmscreditcardervice.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckRequest;
import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSCreditCheckConverter implements MessageConverter {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(JMSCreditCheckConverter.class);

    ObjectMapper mapper;

    public JMSCreditCheckConverter() {
        mapper = new ObjectMapper();
    }

    @Override
    public Message toMessage(Object object, Session session)
            throws JMSException {
        JMSCreditCheckResponse response= (JMSCreditCheckResponse) object; String payload = null;
        try {
            payload = mapper.writeValueAsString(response);
            LOGGER.info("outbound json='{}'", payload);
        } catch (JsonProcessingException e) {
            LOGGER.error("error converting form request", e);
        }

        TextMessage message = session.createTextMessage();
        message.setText(payload);

        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        String payload = textMessage.getText();
        LOGGER.info("inbound json='{}'", payload);

        JMSCreditCheckResponse response = null;
        try {
            response = mapper.readValue(payload, JMSCreditCheckResponse.class);
        } catch (Exception e) {
            LOGGER.error("error converting to request", e);
        }

        return response;
    }
}
