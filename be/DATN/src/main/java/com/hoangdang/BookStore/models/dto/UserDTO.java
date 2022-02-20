package com.hoangdang.BookStore.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id ;
    private String firstName;
    private String lastName;
    private boolean gender;
    private LocalDateTime birthday;
    private String phone;
    private String address;
}
