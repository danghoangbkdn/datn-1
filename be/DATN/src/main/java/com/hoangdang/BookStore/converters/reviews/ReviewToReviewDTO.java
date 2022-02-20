package com.hoangdang.BookStore.converters.reviews;

import com.hoangdang.BookStore.models.dao.Review;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.ReviewDTO;
import org.springframework.stereotype.Component;

@Component
public class ReviewToReviewDTO extends Converter<Review, ReviewDTO> {
    @Override
    public ReviewDTO convert(Review source) {
        return new ReviewDTO(
                source.getId(),
                source.getContent(),
                source.getRating(),
                source.getUser().getId(),
                source.getUser().getLastName() + " " + source.getUser().getFirstName(),
                source.getProduct().getId()
        );
    }
}
