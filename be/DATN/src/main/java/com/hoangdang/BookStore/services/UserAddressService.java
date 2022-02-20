package com.hoangdang.BookStore.services;

import com.hoangdang.BookStore.models.dao.Address;
import com.hoangdang.BookStore.models.dao.UserAddress;
import com.hoangdang.BookStore.models.dto.UserAddressDTO;

import java.util.List;

public interface UserAddressService {
    List<Address> getAllAddress();
    List<UserAddress> getAllByUserId(int userId);
    UserAddress getUserAddressById(int userId, int id);
    UserAddress postUserAddress(int userId, UserAddressDTO userAddressDTO);
    UserAddress putUserAddress(int userId, int id, UserAddressDTO userAddressDTO);
    void deleteUserAddress(int userId, int id);
}
