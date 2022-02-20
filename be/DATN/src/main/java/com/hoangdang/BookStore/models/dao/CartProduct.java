package com.hoangdang.BookStore.models.dao;

import com.hoangdang.BookStore.models.compositKeys.CartProductKey;
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
@IdClass(CartProductKey.class)
public class CartProduct implements Serializable {
    @Id
    private int cartId;

    @Id
    private int productId;
    private int quantity;

    @Override
    public int hashCode() {
        return Objects.hash(cartId, productId, quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CartProduct cartProduct = (CartProduct) obj;
        return cartId == cartProduct.cartId &&
                productId == cartProduct.productId &&
                quantity == cartProduct.quantity;
    }
}
