package com.dhanush.paymentservices.Entity;

import com.dhanush.paymentservices.modal.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class PaymentDetail {
    private PaymentType paymentType;
    private Double paidAmount;
}
