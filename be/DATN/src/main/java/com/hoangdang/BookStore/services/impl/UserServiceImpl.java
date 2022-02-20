package com.hoangdang.BookStore.services.impl;

import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.exceptions.NotFoundException;
import com.hoangdang.BookStore.models.dao.User;
import com.hoangdang.BookStore.repositories.UserRepository;
import com.hoangdang.BookStore.models.dto.UserDTO;
import com.hoangdang.BookStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Converter<UserDTO, User> userConverter;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(int id) {
        verifyUserIdExist(id);
        return userRepository.findById(id).get();
    }

    @Override
    public User postUser(UserDTO userDTO) {
        return userRepository.save(userConverter.convert(userDTO));
    }

    @Override
    public User putUserById(int id, UserDTO userDTO) {
        verifyUserIdExist(id);

        userDTO.setId(id);
        return userRepository.save(userConverter.convert(userDTO));
    }

    @Override
    public void deleteUserById(int id) {
        verifyUserIdExist(id);
        userRepository.delete(userRepository.findById(id).get());
    }

    @Override
    public void verifyUserIdExist(int id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(String.format("User id %d is not found", id));
        }
    }
}
