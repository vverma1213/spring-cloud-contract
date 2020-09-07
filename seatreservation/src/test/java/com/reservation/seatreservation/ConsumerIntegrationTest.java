package com.reservation.seatreservation;

import com.reservation.seatreservation.domain.AvailabilityResponse;
import com.reservation.seatreservation.message.SeatAvailabilityConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConsumerIntegrationTest {

    private static String trainId = "12";

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    SeatAvailabilityConsumer consumer;

    @MockBean
    private PrintService service;

    @Test
    public void shouldParseAvailabilityResponse() throws InterruptedException {
        AvailabilityResponse response = sendTestAvailabilityResponse();
        verify(service, times(1)).printResult(eq(response));
    }

    private AvailabilityResponse sendTestAvailabilityResponse() {
        AvailabilityResponse response = new AvailabilityResponse();
        response.setTrainId(trainId);
        response.setAvailableSeats(3);
        jmsTemplate.convertAndSend("seatAvailability", response);
        return response;
    }
}
