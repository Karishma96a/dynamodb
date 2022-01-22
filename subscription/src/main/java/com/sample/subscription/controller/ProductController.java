package com.sample.subscription.controller;

import com.sample.subscription.model.Product;
import com.sample.subscription.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "Operations pertaining to products")
public class ProductController {

    private final ProductService productService;

    @ApiOperation(value = "Add a product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added product"),
            @ApiResponse(code = 406, message = "Invalid product data")
    })
    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody final Product product) {
        productService.createProduct(product);
        return ResponseEntity.ok("Product was created");
    }
}