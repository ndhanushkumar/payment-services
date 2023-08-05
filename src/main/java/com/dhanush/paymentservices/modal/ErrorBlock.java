package com.dhanush.paymentservices.modal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorBlock {
    private final String name_space="cxp-payment-service";
    private String message;
    private String id;
}
