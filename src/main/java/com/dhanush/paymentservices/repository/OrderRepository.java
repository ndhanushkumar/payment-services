package com.dhanush.paymentservices.repository;


import com.dhanush.paymentservices.Entity.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveMongoRepository<Order,String> {

    Flux<Order> findByCartId(String cartId);
}
