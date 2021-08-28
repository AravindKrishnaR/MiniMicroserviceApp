package com.example.inventoryservice;

import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    List<Inventory> inventoryList = new ArrayList<Inventory>();

    @GetMapping("/inventory/{productid}")
    public Inventory getInventoryDetails(@PathVariable Long productid) {
        Inventory inventory = getInventoryInfo(productid);
        return inventory;
    }

    private Inventory getInventoryInfo(Long productid) {
        populateInventoryList();

        for (Inventory i : inventoryList) {
            if (productid.equals(i.getProductID())) {
                return i;
            }
        }

        return null;
    }

    private void populateInventoryList() {
        inventoryList.add(new Inventory(201L, 1L, true));
        inventoryList.add(new Inventory(202L, 2L, false));
        inventoryList.add(new Inventory(203L, 3L, true));
        inventoryList.add(new Inventory(204L, 4L, false));
        inventoryList.add(new Inventory(205L, 5L, true));
    }
}
