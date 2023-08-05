package com.dhanush.paymentservices.util;

import com.dhanush.paymentservices.Entity.Order;
import com.dhanush.paymentservices.modal.PaymentResponse;

public class UtilFunctions {

    public static PaymentResponse toDto(Order order){
        return new PaymentResponse(order.getCartId(), order.getBalanceAmount(),
                order.getBalanceAmount()==0.0,null);
    }
}
