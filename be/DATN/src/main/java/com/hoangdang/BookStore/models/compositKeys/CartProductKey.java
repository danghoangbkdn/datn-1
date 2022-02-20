package com.hoangdang.BookStore.models.compositKeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductKey implements Serializable {
    private int cartId;
    private int productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProductKey cartProductKey = (CartProductKey) o;
        return cartId == cartProductKey.cartId &&
                productId == cartProductKey.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, productId);
    }

}
