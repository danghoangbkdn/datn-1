package com.hoangdang.BookStore.converters.userAddress;

import com.hoangdang.BookStore.models.dao.Address;
import com.hoangdang.BookStore.models.dao.UserAddress;
import com.hoangdang.BookStore.models.dto.UserAddressDTO;
import com.hoangdang.BookStore.repositories.AddressRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAddressToUserAddressDTO extends Converter<UserAddress, UserAddressDTO> {
    @Autowired
    AddressRepository addressRepository;

    @Override
    public UserAddressDTO convert(UserAddress source) {
        Address address = addressRepository.getOne(source.getAddressId());
        return new UserAddressDTO(
                source.getUserId(),
                source.getAddressId(),
                address.getCountry(),
                address.getProvince(),
                address.getDistrict(),
                address.getWard(),
                address.getStreetName(),
                address.getStreetNumber(),
                source.getAddressType(),
                source.isDefaultAddress()
        );
    }
}
