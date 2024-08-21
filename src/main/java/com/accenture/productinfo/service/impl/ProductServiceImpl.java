package com.accenture.productinfo.service.impl;


import com.accenture.productinfo.dto.ProductIdResponse;
import com.accenture.productinfo.exception.BadRequestException;
import com.accenture.productinfo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {

    private Map<String, String> productData = new HashMap<>();
    public String message;

    public Map<String, String> readFile() {
        ClassPathResource resource = new ClassPathResource("product.json");

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            if (!resource.exists()) {
                message = "Unable to read JSON File";
                throw new BadRequestException(
                        500,
                        message,
                        Map.of()
                );
            }
            productData = objectMapper.readValue(resource.getInputStream(), Map.class);
        } catch (IOException e) {
           return productData;
        }

        return productData;
    }
    @Override
    public ProductIdResponse getProductId(String productCode) {


        productData = readFile();
        String productId = productData.get(productCode);

        if(productId == null){
            message = "Product code " + productCode + " not found.";

            throw new BadRequestException(
                    404,
                    message,
                    Map.of()
            );
        }
        return new ProductIdResponse(productId);
    }

}