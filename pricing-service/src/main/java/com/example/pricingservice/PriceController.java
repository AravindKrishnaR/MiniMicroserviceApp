package com.example.pricingservice;

import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {

    List<Price> priceList = new ArrayList<Price>();

    @GetMapping("/price/{productid}")
    public Price getPriceDetails(@PathVariable Long productid) {
        Price price = getPriceInfo(productid);
        return price;
    }

    private Price getPriceInfo(Long productid) {
        populatePriceList();

        for (Price p : priceList) {
            if (productid.equals(p.getProductID())) {
                return p;
            }
        }

        return null;
    }

    private void populatePriceList() {
        priceList.add(new Price(101L, 1L, 1999, 999));
        priceList.add(new Price(102L, 2L, 2999, 1999));
        priceList.add(new Price(103L, 3L, 3999, 2999));
        priceList.add(new Price(104L, 4L, 4999, 3999));
        priceList.add(new Price(105L, 5L, 5999, 4999));
    }
}
