package com.hoangdang.BookStore.models.dto;

import com.hoangdang.BookStore.models.dao.Promotion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private String supplier;
    private String description;
    private double originPrice;
    private int quantity;
    private String image;
    private File[] files;
    private List<CategoryDTO> categories;
    private List<Promotion> promotions;
}
