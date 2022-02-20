package com.hoangdang.BookStore.services;

import com.hoangdang.BookStore.models.dao.Product;
import com.hoangdang.BookStore.models.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    List<Product> getProductsByNameContaining(String name);
    List<Product> getProductsByCategory(int categoryId);
    Product getById(int id);
    Product postProduct(ProductDTO productDTO);
    Product putProductById(int id, ProductDTO productDTO);
    void deleteProductById(int id);
    void verifyProductIdExist(int id);
}
