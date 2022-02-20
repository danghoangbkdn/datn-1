package com.hoangdang.BookStore.configurations;

import com.hoangdang.BookStore.models.dao.Account;
import com.hoangdang.BookStore.models.dao.Role;
import com.hoangdang.BookStore.models.dao.User;
import com.hoangdang.BookStore.repositories.AccountRepository;
import com.hoangdang.BookStore.repositories.RoleRepository;
import com.hoangdang.BookStore.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${jwt-key}")
    private String signingKey;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    private void addRoleIfMissing(String name){
        if (roleRepository.findByName(name) == null) {
            roleRepository.save(new Role(0, name));
        }
    }

    private void addUserIfMissing(String email, String password, String role){
        if (accountRepository.findByEmail(email) == null) {
            User user = userRepository.save(new User());
            Account account = new Account(0, email, new BCryptPasswordEncoder().encode(password), null,
                    true, null, user.getId(), null, user.getId(), user, roleRepository.findByName(role));

            accountRepository.save(account);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        addRoleIfMissing("ROOT_ADMIN");
        addRoleIfMissing("ADMIN");
        addRoleIfMissing("USER");

        addUserIfMissing("rootadmin@gmail.com", "1234", "ROOT_ADMIN");
        addUserIfMissing("admin1@gmail.com", "12345", "ADMIN");
        addUserIfMissing("admin2@gmail.com", "12345", "ADMIN");
        addUserIfMissing("user1@gmail.com", "123456", "USER");
        addUserIfMissing("user2@gmail.com", "123456", "USER");

        if (signingKey == null || signingKey.length() == 0) {
            String jws = Jwts.builder()
                    .setSubject("BookStore")
                    .signWith(SignatureAlgorithm.HS256, "BookStoreApi").compact();

            System.out.println("Use this jwt key:");
            System.out.println("jwt-key=" + jws);
        }
    }
}
