package com.hoangdang.BookStore.services;

import com.hoangdang.BookStore.models.dao.Account;
import com.hoangdang.BookStore.models.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<Account> getAll();
    Account getById(int id);
    Account login(String email, String token);
    Account signUp(AccountDTO accountDTO);
    Account putAccountById(int id, AccountDTO accountDTO);
    void deleteAccountById(int id);
}
