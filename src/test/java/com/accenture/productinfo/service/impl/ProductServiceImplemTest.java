package com.accenture.productinfo.service.impl;


import com.accenture.productinfo.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductServiceImplemTest {


    @Test
    void contextLoads() {
    }

    @Test
    void testGetProductId_ValidProductCode(){
        ProductServiceImpl ps = new ProductServiceImpl();
        assertEquals("1234567", ps.getProductId("123456").getProductId());
    }

    @Test
    void testGetProductId_InvalidProductCode(){
        ProductServiceImpl ps = new ProductServiceImpl();
        assertThrows(BadRequestException.class, () -> ps.getProductId("12345"));
    }
}
