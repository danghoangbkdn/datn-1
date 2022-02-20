package com.hoangdang.BookStore.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private int id ;
    private String email;
    private String password;
    private String token;
    private boolean active;
    private LocalDateTime createAt;
    private int createBy;
    private LocalDateTime updateAt;
    private int updateBy;
    private int userId;
    private String role;
}
