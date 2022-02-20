package com.hoangdang.BookStore.contants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleConstant {
    ROLE_ROOT_ADMIN("ROOT_ADMIN"),
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    private String role;
}
