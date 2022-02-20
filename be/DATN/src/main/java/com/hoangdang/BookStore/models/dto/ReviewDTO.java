package com.hoangdang.BookStore.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private int id;
    private String content;
    private String rating;
    private int userId;
    private String username;
    private int productId;
}
