package com.hoangdang.BookStore.converters.reviews;

import com.hoangdang.BookStore.models.dao.Review;
import com.hoangdang.BookStore.repositories.ProductRepository;
import com.hoangdang.BookStore.repositories.UserRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewDTOToReview extends Converter<ReviewDTO, Review> {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Review convert(ReviewDTO source) {
        return new Review(
                source.getId(),
                source.getContent(),
                source.getRating(),
                userRepository.getOne(source.getUserId()),
                productRepository.getOne(source.getProductId())
        );
    }
}
