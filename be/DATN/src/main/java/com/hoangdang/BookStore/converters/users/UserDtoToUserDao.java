package com.hoangdang.BookStore.converters.users;

import com.hoangdang.BookStore.models.dao.User;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserDao extends Converter<UserDTO, User> {
    @Override
    public User convert(UserDTO source) {
        return new User(
                source.getId(),
                source.getFirstName(),
                source.getLastName(),
                source.isGender(),
                source.getBirthday(),
                source.getPhone()
        );
    }
}
