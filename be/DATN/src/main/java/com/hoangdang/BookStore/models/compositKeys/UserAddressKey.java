package com.hoangdang.BookStore.models.compositKeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressKey implements Serializable {
    private int userId;
    private int addressId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAddressKey userAddressKey = (UserAddressKey) o;
        return userId == userAddressKey.userId &&
                addressId == userAddressKey.addressId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, addressId);
    }
}
