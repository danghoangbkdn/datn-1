package com.hoangdang.BookStore.converters.userAddress;

import com.hoangdang.BookStore.models.dao.UserAddress;
import com.hoangdang.BookStore.models.dto.UserAddressDTO;
import com.hoangdang.BookStore.repositories.AddressRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAddressDTOToUserAddress extends Converter<UserAddressDTO, UserAddress> {
    @Autowired
    AddressRepository addressRepository;

    @Override
    public UserAddress convert(UserAddressDTO source) {
        return new UserAddress(
                source.getUserId(),
                source.getAddressId(),
                source.getAddressType(),
                source.isDefaultAddress());
    }
}
