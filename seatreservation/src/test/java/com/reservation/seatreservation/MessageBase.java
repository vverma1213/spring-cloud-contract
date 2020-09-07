package com.reservation.seatreservation;

import com.reservation.seatreservation.message.SeatReservationProducer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMessageVerifier
public class MessageBase {
    @Autowired
    JmsMessageVerifier messaging;
    @Autowired
    SeatReservationProducer seatReservationProducer;

    public void testSend() {
        seatReservationProducer.send("12");
    }
}
