package com.reservation.seatreservation.message;

import com.reservation.seatreservation.PrintService;
import com.reservation.seatreservation.domain.AvailabilityResponse;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SeatAvailabilityConsumer {

    private PrintService printerService;

    public SeatAvailabilityConsumer(PrintService printService) {
        this.printerService = printService;
    }

    @JmsListener(destination = "seatAvailability")
    public void receiveMessage(AvailabilityResponse response) {
        printerService.printResult(response);

    }
}
