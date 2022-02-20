package com.hoangdang.BookStore.controllers;

import com.hoangdang.BookStore.configurations.TokenProvider;
import com.hoangdang.BookStore.models.dao.Account;
import com.hoangdang.BookStore.repositories.AccountRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.AccountDTO;
import com.hoangdang.BookStore.models.dto.Login;
import com.hoangdang.BookStore.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private Converter<Account, AccountDTO> accountDTOConverter;

//    @GetMapping("/login/oauth2/code/google")
//    public String googleLogin(){
//        return "you see this text because you login with google successfully";
//    }

    @PostMapping("api/auth/login")
    public ResponseEntity<AccountDTO> login(@RequestBody Login login) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        AccountDTO accountDTO = accountDTOConverter.convert(accountService.login(login.getEmail(), token));

        return ResponseEntity.ok(accountDTO);
    }

    @PostMapping("api/auth/sign-up")
    public AccountDTO signUp(@RequestBody AccountDTO accountDTO) {
        return accountDTOConverter.convert(accountService.signUp(accountDTO));
    }

    @GetMapping("api/accounts/")
    public List<AccountDTO> getAll(){
        return accountDTOConverter.convert(accountService.getAll());
    }

    @GetMapping("api/accounts/{id}")
    public AccountDTO getById(@PathVariable int id){
        return accountDTOConverter.convert(accountService.getById(id));
    }

    @PutMapping("api/accounts/{id}")
    public AccountDTO put(@PathVariable int id, @RequestBody AccountDTO accountDTO) {
        return accountDTOConverter.convert(accountService.putAccountById(id, accountDTO));
    }

    @DeleteMapping("api/accounts/{id}")
    public void delete(@PathVariable int id) {
        accountService.deleteAccountById(id);
    }
}
