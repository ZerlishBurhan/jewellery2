package com.ecommerce.jewelleryMart.strategy;

import java.util.Comparator;
import java.util.List;

import com.ecommerce.jewelleryMart.model.Product;

public class NameDescStrategy implements SortStrategy {

    @Override
    public void sort(List<Product> products) {
        products.sort(
            Comparator.comparing(
                Product::getName,
                String.CASE_INSENSITIVE_ORDER
            ).reversed()
        );
    }
}
