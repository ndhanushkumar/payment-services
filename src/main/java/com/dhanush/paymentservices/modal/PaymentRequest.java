package com.dhanush.paymentservices.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String cartId;
    private PaymentType paymentType;
    private Double paymentAmount;
    private CardDetails cardDetails;

}
