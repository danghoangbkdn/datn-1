package com.hoangdang.BookStore.controllers;

import com.hoangdang.BookStore.models.dao.Product;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.ProductDTO;
import com.hoangdang.BookStore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private Converter<Product, ProductDTO> productDTOConverter;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAll() {
        System.out.println("work");
        return productDTOConverter.convert(productService.getAll());
    }

    @GetMapping("/search")
    public List<ProductDTO> getProductByNameContain(@RequestParam(defaultValue = "") String name) {
        return productDTOConverter.convert(productService.getProductsByNameContaining(name));
    }

    @GetMapping("/category/{id}")
    public List<ProductDTO> getProductByCategory(@PathVariable int id) {
        return productDTOConverter.convert(productService.getProductsByCategory(id));
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable int id) {
        return productDTOConverter.convert(productService.getById(id));
    }

    @PostMapping
    public ProductDTO post(@RequestBody ProductDTO productDTO) {
        System.out.println(productDTO);
        return productDTOConverter.convert(productService.postProduct(productDTO));
    }


    @PutMapping("/{id}")
    public ProductDTO put(@PathVariable int id, @RequestBody ProductDTO productDTO) {
        return productDTOConverter.convert(productService.putProductById(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.deleteProductById(id);
    }
}
