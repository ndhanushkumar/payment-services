package com.dhanush.paymentservices.modal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cardDetails")
public class CardDetails {
    @Id
    private String id;
    private String cardNumber;
    private String expiryDate;
    private String CVV;

}
