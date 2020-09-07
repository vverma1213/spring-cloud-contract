package com.reservation.seatreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SeatreservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeatreservationApplication.class, args);
	}

}
