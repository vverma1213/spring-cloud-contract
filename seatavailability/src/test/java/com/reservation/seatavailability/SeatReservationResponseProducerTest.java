package com.reservation.seatavailability;

import com.reservation.seatavailability.domain.AvailabilityResponse;
import com.reservation.seatavailability.messaging.SeatReservationResponseProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SeatReservationResponseProducerTest {

    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    public void shouldSendMessageContainingTheAvailabilityResponse() {
        int availableSeats = 3;
        String trainId = "12";
        SeatReservationResponseProducer producer = new SeatReservationResponseProducer(jmsTemplate);
        AvailabilityResponse response = new AvailabilityResponse(trainId, availableSeats);

        producer.send(trainId, availableSeats);

        verify(jmsTemplate, times(1)).convertAndSend(eq("seatAvailability"), eq(response), any(MessagePostProcessor.class));
    }

}
