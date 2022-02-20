package com.hoangdang.BookStore.converters.products;

import com.hoangdang.BookStore.models.dao.Category;
import com.hoangdang.BookStore.models.dao.Product;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.CategoryDTO;
import com.hoangdang.BookStore.models.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductDtoToProductDao extends Converter<ProductDTO, Product> {
    @Autowired
    Converter<CategoryDTO, Category> categoryConverter;

    @Override
    public Product convert(ProductDTO source) {
        return new Product(
                source.getId(),
                source.getName(),
                source.getSupplier(),
                source.getDescription(),
                source.getOriginPrice(),
                source.getImage(),
                categoryConverter.convert(source.getCategories()).stream().collect(Collectors.toSet()),
                source.getPromotions().stream().collect(Collectors.toSet())
        );
    }
}
