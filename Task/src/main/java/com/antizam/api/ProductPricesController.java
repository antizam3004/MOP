package com.antizam.api;

import com.antizam.model.ProductPrices;
import com.antizam.service.ProductPricesService;
import com.fasterxml.jackson.core.JsonProcessingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.*;

@RestController()
@RequestMapping(value="/productPrices")
public class ProductPricesController {

    private final ProductPricesService productPricesService;

    @Autowired
    public ProductPricesController(ProductPricesService productPricesService) {
        this.productPricesService = productPricesService;
    }

    @GetMapping
    public ArrayList<ProductPrices> getData() throws InterruptedException, ExecutionException, JsonProcessingException {
        return productPricesService.getData();
    }

}
