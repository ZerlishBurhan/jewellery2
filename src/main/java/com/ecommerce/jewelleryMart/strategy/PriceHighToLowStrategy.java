package com.ecommerce.jewelleryMart.strategy;

import java.util.List;

import com.ecommerce.jewelleryMart.model.Product;

public class PriceHighToLowStrategy implements SortStrategy {

    @Override
    public void sort(List<Product> products) {
        products.sort((a, b) -> Double.compare(b.getPrice(), a.getPrice()));
    }
}
