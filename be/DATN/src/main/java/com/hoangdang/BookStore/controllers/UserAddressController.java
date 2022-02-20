package com.hoangdang.BookStore.controllers;

import com.hoangdang.BookStore.models.dao.Address;
import com.hoangdang.BookStore.models.dao.UserAddress;
import com.hoangdang.BookStore.models.dto.UserAddressDTO;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.services.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/user-address")
@PreAuthorize("isAuthenticated()")
public class UserAddressController {
    @Autowired
    UserAddressService userAddressService;

    @Autowired
    Converter<UserAddress, UserAddressDTO> userAddressDTOConverter;

    @GetMapping("/address")
    public List<Address> getAllAddress() {
        return userAddressService.getAllAddress();
    }

    @GetMapping("/user/{id}")
    public List<UserAddressDTO> getAddressByUserId(@PathVariable int id) {
        return userAddressDTOConverter.convert(userAddressService.getAllByUserId(id));
    }

    @GetMapping("/user/{userId}/address/{id}")
    public UserAddressDTO getUserAddressById(@PathVariable int userId, @PathVariable int id) {
        return userAddressDTOConverter.convert(userAddressService.getUserAddressById(userId, id));
    }

    @PostMapping("/user/{userId}")
    public UserAddressDTO postUserAddressByUserId(@PathVariable int userId, @RequestBody UserAddressDTO userAddressDTO) {
        return userAddressDTOConverter.convert(userAddressService.postUserAddress(userId, userAddressDTO));
    }

    @PutMapping("/user/{userId}/address/{id}")
    public UserAddressDTO putUserAddress(@PathVariable int userId, @PathVariable int id, @RequestBody UserAddressDTO userAddressDTO) {
        return userAddressDTOConverter.convert(userAddressService.putUserAddress(userId, id, userAddressDTO));
    }

    @DeleteMapping("/user/{userId}/address/{id}")
    public void deleteUserAddress(@PathVariable int userId, @PathVariable int id) {
        userAddressService.deleteUserAddress(userId, id);
    }
}
