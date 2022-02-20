package com.hoangdang.BookStore.models.dao;


import com.hoangdang.BookStore.models.compositKeys.OrderProductKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderProductKey.class)
public class OrderProduct implements Serializable {
    @Id
    private int orderId;
    @Id
    private int productId;
    private int quantity;

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderProduct orderProduct = (OrderProduct) obj;
        return orderId == orderProduct.orderId &&
                productId == orderProduct.productId &&
                 quantity == orderProduct.quantity;
    }
}
