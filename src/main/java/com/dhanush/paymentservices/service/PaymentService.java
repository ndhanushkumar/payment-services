package com.dhanush.paymentservices.service;

import com.dhanush.paymentservices.Entity.Order;
import com.dhanush.paymentservices.Entity.PaymentDetail;
import com.dhanush.paymentservices.exception.PaymentException;
import com.dhanush.paymentservices.modal.PaymentRequest;
import com.dhanush.paymentservices.modal.PaymentResponse;
import com.dhanush.paymentservices.modal.PaymentType;
import com.dhanush.paymentservices.modal.Status;
import com.dhanush.paymentservices.repository.OrderRepository;
import com.dhanush.paymentservices.util.UtilFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ValidateCreditCardService validateCreditCardService;

    public Mono<PaymentResponse> submitPayment(PaymentRequest paymentRequest) {
        // Card validation only for CR (Credit) payment type
        Mono<Status> validationMono = PaymentType.CR.equals(paymentRequest.getPaymentType())
                ? validateCreditCardService.validateCardDetails(paymentRequest.getCardDetails())
                : Mono.just(Status.SUCCESS);

        return validationMono
                .flux()
                .flatMap(status -> orderRepository.findByCartId(paymentRequest.getCartId()))
                .next()
                .map(order -> {
                    try {
                        return submitPaymentImpl(order, paymentRequest);
                    } catch (PaymentException e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(orderRepository::save)
                .map(UtilFunctions::toDto)
                .switchIfEmpty(Mono.error(new PaymentException("Order Not Found")));
    }

    public Order submitPaymentImpl(Order order, PaymentRequest paymentRequest) throws PaymentException {
        if (order.getBalanceAmount() < paymentRequest.getPaymentAmount()) {
            throw new PaymentException("Payment Amount " + paymentRequest.getPaymentAmount() + " exceeds the balance "
                    + order.getBalanceAmount());
        }

        if (order.getPaymentDetails() == null) {
            order.setPaymentDetails(
                    List.of(new PaymentDetail(paymentRequest.getPaymentType(), paymentRequest.getPaymentAmount())));
        } else {
            order.getPaymentDetails()
                    .add(new PaymentDetail(paymentRequest.getPaymentType(), paymentRequest.getPaymentAmount()));
        }

        var balance = order.getBalanceAmount() - paymentRequest.getPaymentAmount();
        if (balance == 0.0) {
            order.setPaymentStatus(Status.SUCCESS);
            order.setOrderStatus(Status.ORDERED);
            order.setOrderDateTime(LocalDateTime.now());
        }
        order.setBalanceAmount(balance);
        return order;
    }

    public Mono<Status> voidPaymentImpl(String orderId) {
        return orderRepository.findById(orderId)
                .flatMap(o -> {
                    o.setPaymentStatus(Status.VOID);
                    return orderRepository.save(o);
                }).map(Order::getPaymentStatus);
    }
}
