package com.hoangdang.BookStore.converters.products;

import com.hoangdang.BookStore.models.dao.Category;
import com.hoangdang.BookStore.models.dao.Entity;
import com.hoangdang.BookStore.models.dao.Product;
import com.hoangdang.BookStore.repositories.EntityRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.CategoryDTO;
import com.hoangdang.BookStore.models.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductDaoToProductDto extends Converter<Product, ProductDTO> {
    @Autowired
    Converter<Category, CategoryDTO> categoryDTOConverter;

    @Autowired
    EntityRepository entityRepository;

    @Override
    public ProductDTO convert(Product source) {
        Entity entity = entityRepository.findFirstByProductId(source.getId());
        return new ProductDTO(
                source.getId(),
                source.getName(),
                source.getSupplier(),
                source.getDescription(),
                source.getOriginPrice(),
                entity == null ? 0 : entity.getQuantity(),
                source.getImage(),
                null,
                categoryDTOConverter.convert(source.getCategories().stream().collect(Collectors.toList())),
                source.getPromotions().stream().collect(Collectors.toList())
        );
    }
}
