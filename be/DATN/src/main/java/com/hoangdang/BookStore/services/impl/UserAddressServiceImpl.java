package com.hoangdang.BookStore.services.impl;

import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.exceptions.NotFoundException;
import com.hoangdang.BookStore.models.dao.Address;
import com.hoangdang.BookStore.models.dao.UserAddress;
import com.hoangdang.BookStore.models.dto.UserAddressDTO;
import com.hoangdang.BookStore.repositories.AddressRepository;
import com.hoangdang.BookStore.repositories.UserAddressRepository;
import com.hoangdang.BookStore.models.compositKeys.UserAddressKey;
import com.hoangdang.BookStore.services.UserAddressService;
import com.hoangdang.BookStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private Converter<UserAddressDTO, UserAddress> userAddressConverter;

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public List<UserAddress> getAllByUserId(int userId) {
        userService.verifyUserIdExist(userId);
        return userAddressRepository.findAllByUserId(userId);
    }

    @Override
    public UserAddress getUserAddressById(int userId, int id) {
        userService.verifyUserIdExist(userId);
        verifyAddressIdExist(id);
        verifyUserAddressIdExist(userId, id);

        return userAddressRepository.findById(new UserAddressKey(userId, id)).get();
    }

    @Override
    public UserAddress postUserAddress(int userId, UserAddressDTO userAddressDTO) {
        userService.verifyUserIdExist(userId);

        Address address = new Address(0, userAddressDTO.getCountry(), userAddressDTO.getProvince(), userAddressDTO.getDistrict(),
                userAddressDTO.getWard(), userAddressDTO.getStreetName(), userAddressDTO.getStreetNumber());

        updateForDefaultAddress(userId, userAddressDTO.isDefaultAddress());

        userAddressDTO.setUserId(userId);
        userAddressDTO.setAddressId(addressRepository.save(address).getId());
        return userAddressRepository.save(userAddressConverter.convert(userAddressDTO));
    }

    @Override
    public UserAddress putUserAddress(int userId, int id, UserAddressDTO userAddressDTO) {
        userService.verifyUserIdExist(userId);
        verifyAddressIdExist(id);
        verifyUserAddressIdExist(userId, id);

        Address address = new Address(id, userAddressDTO.getCountry(), userAddressDTO.getProvince(), userAddressDTO.getDistrict(),
                userAddressDTO.getWard(), userAddressDTO.getStreetName(), userAddressDTO.getStreetNumber());
        addressRepository.save(address);

        updateForDefaultAddress(userId, userAddressDTO.isDefaultAddress());
        userAddressDTO.setUserId(userId);
        userAddressDTO.setAddressId(id);

        return userAddressRepository.save(userAddressConverter.convert(userAddressDTO));
    }

    @Override
    public void deleteUserAddress(int userId, int id) {
        userService.verifyUserIdExist(userId);
        verifyAddressIdExist(id);
        verifyUserAddressIdExist(userId, id);

        userAddressRepository.deleteById(new UserAddressKey(userId, id));
        addressRepository.deleteById(id);
    }

    private void verifyAddressIdExist(int id) {
        if (!addressRepository.existsById(id)) {
            throw new NotFoundException(String.format("Not found address"));
        }
    }

    private void verifyUserAddressIdExist(int userId, int id) {
        if (!userAddressRepository.existsById(new UserAddressKey(userId, id))) {
            throw new NotFoundException(String.format("Not found user-address"));
        }
    }

    private void updateForDefaultAddress(int userId, boolean isDefaultAddress) {
        if (isDefaultAddress) {
            getAllByUserId(userId).forEach(userAddress -> {
                userAddress.setDefaultAddress(false);
                userAddressRepository.save(userAddress);
            });
        }
    }
}
