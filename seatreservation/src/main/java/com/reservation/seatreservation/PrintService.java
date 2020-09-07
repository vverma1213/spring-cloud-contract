package com.reservation.seatreservation;

import com.reservation.seatreservation.domain.AvailabilityResponse;
import org.springframework.stereotype.Component;

@Component
public class PrintService {
    public void printResult(final AvailabilityResponse response) {
        System.out.printf("Availability Report: train ID %s, available seats %s", response.getTrainId(), response.getAvailableSeats());
    }
}
