package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByEmail(String email);
    Account findByUserId(int userId);
}
