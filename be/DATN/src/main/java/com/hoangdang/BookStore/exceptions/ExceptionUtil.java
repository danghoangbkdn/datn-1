package com.hoangdang.BookStore.exceptions;

import org.springframework.data.jpa.repository.JpaRepository;

public class ExceptionUtil {
    public static <T> void verify(int id, JpaRepository<T, Integer> repository) {
        if (repository.findById(id) == null) {
            throw new NotFoundException("Not found exception");
        }
    }
}
