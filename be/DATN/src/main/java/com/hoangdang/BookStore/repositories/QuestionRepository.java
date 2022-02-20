package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
