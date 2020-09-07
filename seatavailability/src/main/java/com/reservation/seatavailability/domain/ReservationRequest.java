package com.reservation.seatavailability.domain;

public class ReservationRequest {

    private String trainId;
    private String id;

    public ReservationRequest(String trainId, String id) {
        this.trainId = trainId;
        this.id = id;
    }

    public ReservationRequest() {
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
