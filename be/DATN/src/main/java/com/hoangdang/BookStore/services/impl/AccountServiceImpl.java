package com.hoangdang.BookStore.services.impl;

import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.exceptions.BadRequestException;
import com.hoangdang.BookStore.exceptions.NotFoundException;
import com.hoangdang.BookStore.models.dao.Account;
import com.hoangdang.BookStore.models.dao.User;
import com.hoangdang.BookStore.repositories.AccountRepository;
import com.hoangdang.BookStore.repositories.RoleRepository;
import com.hoangdang.BookStore.repositories.UserRepository;
import com.hoangdang.BookStore.contants.RoleConstant;
import com.hoangdang.BookStore.models.dto.AccountDTO;
import com.hoangdang.BookStore.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Converter<AccountDTO, Account> accountConverter;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(int id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public Account login(String email, String token) {
        Account account = accountRepository.findByEmail(email);
        account.setToken(token);
        return accountRepository.save(account);
    }

    @Override
    public Account signUp(AccountDTO accountDTO) {
        verifyEmailIsUnique(accountDTO.getEmail());

        User user = userRepository.save(new User());
        Account account = new Account(0, accountDTO.getEmail(), new BCryptPasswordEncoder().encode(accountDTO.getPassword()),
                null, true, LocalDateTime.now(), user.getId(), LocalDateTime.now(), user.getId(),
                user, roleRepository.findByName(RoleConstant.ROLE_USER.getRole()));
        System.out.println(account);

        return accountRepository.save(account);
    }

    @Override
    public Account putAccountById(int id, AccountDTO accountDTO) {
        verifyAccountIdExist(id);
        accountDTO.setId(id);
        return accountRepository.save(accountConverter.convert(accountDTO));
    }

    @Override
    public void deleteAccountById(int id) {
        verifyAccountIdExist(id);
        accountRepository.delete(accountRepository.getOne(id));
    }

    private void verifyEmailIsUnique(String email) {
        if (accountRepository.findByEmail(email) != null) {
            throw new BadRequestException(String.format("auth/email-already-in-use"));
        }
    }

    private void verifyAccountIdExist(int id) {
        if (!accountRepository.existsById(id)) {
            throw new NotFoundException(String.format("Account id %d is not found", id));
        }
    }
}
