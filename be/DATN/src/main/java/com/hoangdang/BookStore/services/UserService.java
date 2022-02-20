package com.hoangdang.BookStore.services;

import com.hoangdang.BookStore.models.dao.User;
import com.hoangdang.BookStore.models.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(int id);
    User postUser(UserDTO userDTO);
    User putUserById(int id, UserDTO userDTO);
    void deleteUserById(int id);
    void verifyUserIdExist(int id);
}
