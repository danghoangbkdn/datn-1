package com.hoangdang.BookStore.services;

import com.hoangdang.BookStore.models.dao.Review;
import com.hoangdang.BookStore.models.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<Review> getAllByProduct(int productId);
    Review getById(int id);
    Review post(int userId, int productId, ReviewDTO reviewDTO);
    Review put(int userId, int id, ReviewDTO reviewDTO);
    void delete(int userId, int id);
}
