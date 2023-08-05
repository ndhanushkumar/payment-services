package com.dhanush.paymentservices.repository;

import com.dhanush.paymentservices.modal.CardDetails;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CardRepository extends ReactiveMongoRepository<CardDetails,String> {

    Flux<CardDetails> findByCardNumber(String cardNumber);

}
