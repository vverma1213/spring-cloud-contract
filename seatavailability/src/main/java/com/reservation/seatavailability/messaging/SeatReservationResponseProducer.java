package com.reservation.seatavailability.messaging;

import com.reservation.seatavailability.ReplyToProcessor;
import com.reservation.seatavailability.domain.AvailabilityResponse;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SeatReservationResponseProducer {

    private JmsTemplate jmsTemplate;

    public SeatReservationResponseProducer(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(final String trainId, final int availableSeats) {
        AvailabilityResponse response = new AvailabilityResponse(trainId, availableSeats);
        jmsTemplate.convertAndSend("seatAvailability", response, new ReplyToProcessor());
    }
}
