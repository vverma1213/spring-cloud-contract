package com.reservation.seatreservation.message;

import com.reservation.seatreservation.ReplyToProcessor;
import com.reservation.seatreservation.domain.ReservationRequest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SeatReservationProducer {

    private JmsTemplate jmsTemplate;

    public SeatReservationProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(final String trainId) {
        ReservationRequest request = new ReservationRequest();
        request.setTrainId(trainId);

        jmsTemplate.convertAndSend("seatReservation", request, new ReplyToProcessor());
    }
}
