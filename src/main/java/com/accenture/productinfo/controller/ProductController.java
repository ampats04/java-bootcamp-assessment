package com.accenture.productinfo.controller;

import com.accenture.productinfo.dto.ProductIdResponse;
import com.accenture.productinfo.exception.ApiErrorResponse;
import com.accenture.productinfo.exception.BadRequestException;
import com.accenture.productinfo.service.ProductService;
import com.accenture.productinfo.service.impl.ProductServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
@RequestMapping("ms-product-info")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("getProductId/{productCode}")
    public ResponseEntity<ProductIdResponse> getProductDetails(@PathVariable String productCode) {
        try {
            ProductIdResponse response = productService.getProductId(productCode);
            return ResponseEntity.ok(response);
        } catch (BadRequestException e) {
            throw e;
        }
    }


}
