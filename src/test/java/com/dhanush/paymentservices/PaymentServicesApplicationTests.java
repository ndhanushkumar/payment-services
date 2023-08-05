package com.dhanush.paymentservices;

import com.dhanush.paymentservices.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class PaymentServicesApplicationTests {
	@Autowired
	private CardRepository cardRepository;
	@Test
	void contextLoads() {
	}

	@Test
	void testCard(){

		StepVerifier.create(cardRepository.findByCardNumber("1234567890"))
				.expectNextCount(1)
				.verifyComplete();
	}
}
