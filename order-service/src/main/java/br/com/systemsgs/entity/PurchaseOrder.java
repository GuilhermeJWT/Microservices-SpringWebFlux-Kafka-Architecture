package br.com.systemsgs.entity;

import br.com.systemsgs.event.OrderStatus;
import br.com.systemsgs.event.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PURCHASE_ORDER_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer userId;

    private Integer productId;

    private Integer price;

    /*Vindo lá do projeto COMMON-DTOS*/
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;


}
