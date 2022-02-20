package com.hoangdang.BookStore.converters.accounts;

import com.hoangdang.BookStore.models.dao.Account;
import com.hoangdang.BookStore.repositories.AccountRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoToAccountDao extends Converter<AccountDTO, Account> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account convert(AccountDTO source) {
        Account account = accountRepository.getOne(source.getId());

        return new Account(
                source.getId(),
                source.getEmail(),
                account.getPassword(),
                source.getToken(),
                source.isActive(),
                source.getCreateAt(),
                source.getCreateBy(),
                source.getUpdateAt(),
                source.getUpdateBy(),
                account.getUser(),
                account.getRole()
        );
    }
}
