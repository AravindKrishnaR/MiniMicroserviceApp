package com.example.productservice;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {

    List<ProductInfo> productList = new ArrayList<ProductInfo>();

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/product/details/{productid}")
    public Product getProductDetails(@PathVariable Long productid) {

        // get name and description from product-service
        ProductInfo productInfo = getProductInfo(productid);

        // get price from pricing-service
        Price price = restTemplate.getForObject("http://localhost:8002/price/" + productid, Price.class);

        // get stock availability from inventory-service
        Inventory inventory = restTemplate.getForObject("http://localhost:8003/inventory/" + productid,
                Inventory.class);

        return new Product(productInfo.getProductID(), productInfo.getProductName(), productInfo.getProductDesc(),
                price.getDiscountedPrice(), inventory.getInStock());
    }

    private ProductInfo getProductInfo(Long productid) {
        populateProductList();

        for (ProductInfo p : productList) {
            if (productid.equals(p.getProductID())) {
                return p;
            }
        }

        return null;
    }

    private void populateProductList() {
        productList.add(new ProductInfo(1L, "productName1", "productDesc1"));
        productList.add(new ProductInfo(2L, "productName2", "productDesc2"));
        productList.add(new ProductInfo(3L, "productName3", "productDesc3"));
        productList.add(new ProductInfo(4L, "productName4", "productDesc4"));
        productList.add(new ProductInfo(5L, "productName5", "productDesc5"));
    }
}
