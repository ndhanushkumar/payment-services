package com.dhanush.paymentservices.Entity;


import com.dhanush.paymentservices.modal.CartItem;
import com.dhanush.paymentservices.modal.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "order_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String orderId;
    private String cartId;
    private String userId;
    private Status orderStatus=Status.PENDING;
    private Status inventoryCheckStatus;
    private Status inventoryStatus;
    private Status paymentStatus=Status.PENDING;
    private Double totalAmount;
    private Double balanceAmount;
    private List<CartItem> orderItems;
    private LocalDateTime orderDateTime;

}
