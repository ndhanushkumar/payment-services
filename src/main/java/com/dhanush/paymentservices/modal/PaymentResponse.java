package com.dhanush.paymentservices.modal;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PaymentResponse {
    private String cartId;
    private Double balanceAmount;
    private Boolean fulfillment;
    private ErrorBlock error;

    public PaymentResponse(ErrorBlock error) {
        this.error = error;
    }
}
