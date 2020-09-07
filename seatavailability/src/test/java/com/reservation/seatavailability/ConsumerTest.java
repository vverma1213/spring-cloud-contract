package com.reservation.seatavailability;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.reservation.seatavailability.domain.ReservationRequest;
import com.reservation.seatavailability.messaging.SeatReservationConsumer;
import com.reservation.seatavailability.messaging.SeatReservationResponseProducer;
import com.reservation.seatavailability.service.SeatAvailabilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerTest {

    @Mock
    private SeatAvailabilityService seatAvailabilityService;

    @Mock
    private SeatReservationResponseProducer producer;

    @Test
    public void shouldCallSeatAvailabilityCheckerOnRecievedRequest() throws IOException {
        String trainId = "12";
        ReservationRequest reservationRequest = createReservationRequest(trainId);
        SeatReservationConsumer consumer = new SeatReservationConsumer(seatAvailabilityService, producer);

        consumer.receiveMessage(reservationRequest);

        verify(seatAvailabilityService,times(1)).getFreeSeats(trainId);
    }

    private ReservationRequest createReservationRequest(String trainId) throws JsonProcessingException {
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setTrainId(trainId);
        return reservationRequest;
    }
}
