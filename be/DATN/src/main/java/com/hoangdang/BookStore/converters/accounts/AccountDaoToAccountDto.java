package com.hoangdang.BookStore.converters.accounts;

import com.hoangdang.BookStore.models.dao.Account;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.AccountDTO;
import org.springframework.stereotype.Component;

@Component
public class AccountDaoToAccountDto extends Converter<Account, AccountDTO> {

    @Override
    public AccountDTO convert(Account source) {
        return new AccountDTO(
                source.getId(),
                source.getEmail(),
                null,
                source.getToken(),
                source.isActive(),
                source.getCreateAt(),
                source.getCreateBy(),
                source.getUpdateAt(),
                source.getUpdateBy(),
                source.getUser().getId(),
                source.getRole().getName()
        );
    }
}
