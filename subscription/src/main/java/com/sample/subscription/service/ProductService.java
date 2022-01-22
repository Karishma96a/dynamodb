package com.sample.subscription.service;

import com.sample.subscription.exception.UnsupportedProductException;
import com.sample.subscription.model.Product;
import com.sample.subscription.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public void createProduct(final Product product) {
        if (product == null || product.getProductType() == null) {
            throw new UnsupportedProductException("Invalid product data");
        }

        productRepository.save(product);
    }
}
