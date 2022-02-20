package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
