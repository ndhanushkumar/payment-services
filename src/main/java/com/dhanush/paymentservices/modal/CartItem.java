package com.dhanush.paymentservices.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Integer id;
    private Integer quantity;
    private Status status;
}
