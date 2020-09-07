package com.reservation.seatavailability;

import com.reservation.seatavailability.messaging.SeatReservationConsumer;
import com.reservation.seatavailability.messaging.SeatReservationResponseProducer;
import com.reservation.seatavailability.service.SeatAvailabilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "com.reservation:seatreservation:+:stubs")
public class ConsumerIntegrationTest {

    private static String trainId = "12";

    @Autowired
    StubFinder stubFinder;

    @Autowired
    SeatReservationConsumer consumer;

    @MockBean
    private SeatAvailabilityService service;

    @MockBean
    private SeatReservationResponseProducer producer;

    @Test
    public void shouldParseReservationRequest() throws InterruptedException {
        stubFinder.trigger("accepted_verification");

        CountDownLatch countDownLatch = new CountDownLatch(1);
        consumer.setTestCountDownLatch(countDownLatch);

        countDownLatch.await(5, TimeUnit.SECONDS);
        verify(service, times(1)).getFreeSeats(trainId);
    }

}
