package com.reservation.seatavailability;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reservation.seatavailability.domain.AvailabilityResponse;
import com.reservation.seatavailability.messaging.SeatReservationResponseProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResponseProducerIntegrationTest {

    @Autowired
    private SeatReservationResponseProducer producer;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldSendMessageWithTrainId() throws JMSException, IOException {
        String trainId = "12";
        int availableSeats = 3;
        producer.send(trainId, availableSeats);

        jmsTemplate.setReceiveTimeout(1000);
        Message message = jmsTemplate.receive("seatAvailability");
        TextMessage textMessage = (TextMessage) message;

        AvailabilityResponse response = mapper.readValue(textMessage.getText(), AvailabilityResponse.class);
        assertThat(response.getTrainId()).isEqualTo(trainId);
        assertThat(response.getAvailableSeats()).isEqualTo(availableSeats);
    }
}
