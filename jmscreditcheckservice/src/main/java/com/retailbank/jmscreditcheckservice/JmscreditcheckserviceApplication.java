package com.retailbank.jmscreditcheckservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class JmscreditcheckserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmscreditcheckserviceApplication.class, args);
	}

}
