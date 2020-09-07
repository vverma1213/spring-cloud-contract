package com.reservation.seatreservation.domain;

public class ReservationRequest {
    private String trainId="12";

    public ReservationRequest(String trainId) {
        this.trainId = trainId;
    }

    public ReservationRequest() {
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }
}
