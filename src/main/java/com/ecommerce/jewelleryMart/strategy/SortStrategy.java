package com.ecommerce.jewelleryMart.strategy;

import java.util.List;

import com.ecommerce.jewelleryMart.model.Product;

public interface SortStrategy {
    void sort(List<Product> products);
}
