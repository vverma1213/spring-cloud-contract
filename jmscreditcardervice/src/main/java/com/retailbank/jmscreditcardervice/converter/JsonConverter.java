package com.retailbank.jmscreditcardervice.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailbank.jmscreditcardervice.domain.JMSCreditCheckResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;

@Component
public class JsonConverter implements MessageConverter {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(JsonConverter.class);

    ObjectMapper mapper;

    public JsonConverter() {
        mapper = new ObjectMapper();
    }

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        String payload = null;

        try {
            payload = mapper.writeValueAsString(object);
            LOGGER.info("outbound json='{}'", payload);
        } catch (JsonProcessingException e) {
            LOGGER.error("error converting form response", e);
        }

        TextMessage message = session.createTextMessage();
        message.setText(payload);

        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        TextMessage textMessage = (TextMessage) message;
        String payload = textMessage.getText();
        LOGGER.info("inbound json='{}'", payload);
        JMSCreditCheckResponse response=null;
        try {
            response = mapper.readValue(payload, JMSCreditCheckResponse.class);
        }catch(IOException e){
            LOGGER.error("error converting to response", e);
        }
        return response;
    }
}
