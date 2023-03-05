package com.notifications.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ProductDTOTest
{

    @Test
    public void TestQuantity(){
        ProductDTO testproduct = new ProductDTO();
        testproduct.setPrice(200);
        testproduct.setQuantity(10);
        int totPrice = testproduct.getTotalPrice();
        Assertions.assertEquals(2000, totPrice);
    }
}
