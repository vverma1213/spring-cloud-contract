package com.reservation.seatavailability.messaging;

import com.reservation.seatavailability.domain.ReservationRequest;
import com.reservation.seatavailability.service.SeatAvailabilityService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class SeatReservationConsumer {

    private SeatAvailabilityService availabilityService;
    private CountDownLatch testCountDownLatch;

    private SeatReservationResponseProducer producer;

    public SeatReservationConsumer(SeatAvailabilityService availabilityService, SeatReservationResponseProducer producer) {
        this.availabilityService = availabilityService;
        this.producer = producer;
    }

    public void setTestCountDownLatch(CountDownLatch testCountDownLatch){
        this.testCountDownLatch = testCountDownLatch;
    }

    @JmsListener(destination = "seatReservation")
    public void receiveMessage(ReservationRequest request) {
        String trainId = request.getTrainId();
        triggerCountdownLatch();
        int freeSeats = availabilityService.getFreeSeats(trainId);
        producer.send(trainId, freeSeats);
    }

    private void triggerCountdownLatch() {
        if(testCountDownLatch != null) {
            testCountDownLatch.countDown();
        }
    }
}
