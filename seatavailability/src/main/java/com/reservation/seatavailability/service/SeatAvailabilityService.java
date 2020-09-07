package com.reservation.seatavailability.service;

import org.springframework.stereotype.Service;

@Service
public class SeatAvailabilityService {
    public int getFreeSeats(String traindId) {
        return 3;
    }
}
