package com.dhanush.paymentservices.controller;

import com.dhanush.paymentservices.modal.PaymentRequest;
import com.dhanush.paymentservices.modal.PaymentResponse;
import com.dhanush.paymentservices.modal.Status;
import com.dhanush.paymentservices.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("payment-services")
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @PostMapping("/submit-payment")
    public Mono<PaymentResponse> submitPayment(@RequestBody PaymentRequest paymentRequest){
        return paymentService.submitPayment(paymentRequest);

    }
    @GetMapping("/void-payment/{orderId}")
    public Mono<Status> voidPayment(@PathVariable String orderId){
        return paymentService.voidPaymentImpl(orderId);

    }
}
