package com.hoangdang.BookStore.controllers;

import com.hoangdang.BookStore.models.dao.User;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.UserDTO;
import com.hoangdang.BookStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/users")
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    private Converter<User, UserDTO> userDTOConverter;

    @Autowired
    private UserService userService;

    @GetMapping
    @Secured(value = {"ROOT_ADMIN", "ADMIN"})
    public List<UserDTO> getAllUser() {
        return userDTOConverter.convert(userService.getAll());
    }

    @GetMapping("/{id}")
    @Secured("USER")
    public UserDTO getUserById(@PathVariable int id) {
        return userDTOConverter.convert(userService.getById(id));
    }

    @PostMapping
    public UserDTO post(@RequestBody UserDTO userDTO) {
        return userDTOConverter.convert(userService.postUser(userDTO));
    }

    @PutMapping("/{id}")
    public UserDTO put(@PathVariable int id, @RequestBody UserDTO userDTO) {
        return userDTOConverter.convert(userService.putUserById(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.deleteUserById(id);
    }
}
