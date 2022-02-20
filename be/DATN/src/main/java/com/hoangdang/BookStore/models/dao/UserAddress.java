package com.hoangdang.BookStore.models.dao;

import com.hoangdang.BookStore.models.compositKeys.UserAddressKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_address")
@IdClass(UserAddressKey.class)
public class UserAddress implements Serializable {
    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "address_id")
    private int addressId;

    private String addressType;

    @Column(name = "is_default")
    private boolean defaultAddress;

    @Override
    public int hashCode() {
        return Objects.hash(userId, addressId, addressType, defaultAddress);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserAddress userAddress = (UserAddress) obj;
        return userId == userAddress.userId &&
                addressId == userAddress.addressId &&
                addressType == userAddress.addressType &&
                defaultAddress == userAddress.defaultAddress;
    }
}
