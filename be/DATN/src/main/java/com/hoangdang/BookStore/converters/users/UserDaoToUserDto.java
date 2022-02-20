package com.hoangdang.BookStore.converters.users;

import com.hoangdang.BookStore.models.dao.User;
import com.hoangdang.BookStore.models.dao.UserAddress;
import com.hoangdang.BookStore.models.dto.UserAddressDTO;
import com.hoangdang.BookStore.repositories.UserAddressRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoToUserDto extends Converter<User, UserDTO> {
    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private Converter<UserAddress, UserAddressDTO> addressDTOConverter;

    @Override
    public UserDTO convert(User source) {
        UserAddressDTO userAddress = addressDTOConverter.convert(userAddressRepository.findByUserIdAndDefaultAddress(source.getId(), true));
        String address = userAddress.getStreetNumber() + ", " + userAddress.getStreetName() + ", " + userAddress.getWard()
                + ", " + userAddress.getDistrict() + ", " + userAddress.getProvince();

        return new UserDTO(
                source.getId(),
                source.getFirstName(),
                source.getLastName(),
                source.isGender(),
                source.getBirthday(),
                source.getPhone(),
                address
        );
    }
}
