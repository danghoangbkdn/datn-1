package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
