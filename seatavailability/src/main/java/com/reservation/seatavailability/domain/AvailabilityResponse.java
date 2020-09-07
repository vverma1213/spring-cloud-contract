package com.reservation.seatavailability.domain;


public class AvailabilityResponse {

    private String trainId;
    private int availableSeats;

    public AvailabilityResponse(String trainId, int availableSeats) {
        this.trainId = trainId;
        this.availableSeats = availableSeats;
    }

    public AvailabilityResponse() {
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
