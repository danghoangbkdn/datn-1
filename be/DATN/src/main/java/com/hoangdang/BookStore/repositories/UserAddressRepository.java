package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.UserAddress;
import com.hoangdang.BookStore.models.compositKeys.UserAddressKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress, UserAddressKey> {
    List<UserAddress> findAllByUserId(int id);
    UserAddress findByUserIdAndDefaultAddress(int id, boolean isDefault);
}
