package com.hoangdang.BookStore.services.impl;

import com.google.api.services.drive.model.File;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.exceptions.NotFoundException;
import com.hoangdang.BookStore.models.dao.Product;
import com.hoangdang.BookStore.repositories.CategoryRepository;
import com.hoangdang.BookStore.repositories.ProductRepository;
import com.hoangdang.BookStore.utils.CreateFolder;
import com.hoangdang.BookStore.utils.CreateGoogleFile;
import com.hoangdang.BookStore.utils.GetSubFoldersByName;
import com.hoangdang.BookStore.utils.ShareGoogleFile;
import com.hoangdang.BookStore.models.dto.EntityDTO;
import com.hoangdang.BookStore.models.dto.ProductDTO;
import com.hoangdang.BookStore.services.EntityService;
import com.hoangdang.BookStore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    EntityService entityService;

    @Autowired
    private Converter<ProductDTO, Product> productConverter;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        return productRepository.findAllByCategories(categoryRepository.getOne(categoryId));
    }

    @Override
    public Product getById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product postProduct(ProductDTO productDTO) {
        Product product = productRepository.save(productConverter.convert(this.setProductImage(productDTO)));
        entityService.post(new EntityDTO(0, 0, product.getId(), LocalDateTime.now(), 0, 0));
        return product;
    }

    @Override
    public Product putProductById(int id, ProductDTO productDTO) {
        verifyProductIdExist(id);

        productDTO.setId(id);
        return productRepository.save(productConverter.convert(this.setProductImage(productDTO)));
    }

    @Override
    public void deleteProductById(int id) {
        verifyProductIdExist(id);
        productRepository.delete(productRepository.getOne(id));
    }

    @Override
    public void verifyProductIdExist(int id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException(String.format("Product id %d is not found", id));
        }
    }

    private ProductDTO setProductImage(ProductDTO productDTO) {
        if (productDTO.getFiles() != null && productDTO.getFiles().length > 0) {
            List<File> folders = null;
            try {
                folders = GetSubFoldersByName.getGoogleSubFolderByName(null, "BookstoreDrive");
                folders.forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String parentFolderId = folders.get(0).getId();
            String regex = "\\s+";
            String productFolder = productDTO.getName().replaceAll(regex, "-");

            // Check if Product's folder is exist? else create
            try {
                folders = GetSubFoldersByName.getGoogleSubFolderByName(parentFolderId, productFolder);
                folders.forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // if folder not exist create new
            if (folders.size() > 0 && folders.get(0).getId() == null) {
                try {
                    folders.add(CreateFolder.createGoogleFolder(parentFolderId, productFolder));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Create Google File:
            for (java.io.File file : productDTO.getFiles()) {
                try {
                    Calendar cal = Calendar.getInstance();
                    File googleFile = CreateGoogleFile.createGoogleFile(folders.get(0).getId(), "image/jpeg",
                            productFolder + cal.getTimeInMillis(), file);
                    ShareGoogleFile.createPublicPermission(googleFile.getId());
                    productDTO.getImage().concat("," + googleFile.getWebViewLink());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return productDTO;
    }
}
