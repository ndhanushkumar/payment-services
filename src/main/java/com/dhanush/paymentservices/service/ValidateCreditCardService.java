package com.dhanush.paymentservices.service;

import com.dhanush.paymentservices.exception.PaymentException;
import com.dhanush.paymentservices.modal.CardDetails;
import com.dhanush.paymentservices.modal.Status;
import com.dhanush.paymentservices.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class ValidateCreditCardService {
    @Autowired
    private CardRepository cardRepository;

    public Mono<Status> validateCardDetails(CardDetails cardDetails){

        return cardRepository.findByCardNumber(cardDetails.getCardNumber())
                .next()
                .map(cardDetails1 -> {

                    if(cardDetails1.getCVV().equals(cardDetails.getCVV())){
                        var cardyear=Integer.parseInt(cardDetails1.getExpiryDate().substring(3));
                        var currentYear=LocalDate.now().getYear();
                        var year= cardyear > currentYear;
                        var month=true;
                        if(cardyear== currentYear) {
                            year=true;
                            month=Integer.parseInt(cardDetails1.getExpiryDate().substring(0,2))>= LocalDate.now().getMonthValue();
                        }

                        if(year && month){
                            return Status.SUCCESS;
                        }
                    }
                    return Status.FAILURE;

                }).filter(status -> status.equals(Status.SUCCESS))
                .switchIfEmpty(Mono.error(new PaymentException("Card Validation Failed")));



    }
}
