package br.com.paymentmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("dev")
public class PaymentManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentManagerApplication.class, args);
	}

}
