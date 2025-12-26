package com.ecommerce.jewelleryMart.service;

import com.ecommerce.jewelleryMart.model.Cart;
import com.ecommerce.jewelleryMart.model.CartItem;
import com.ecommerce.jewelleryMart.model.Product;
import com.ecommerce.jewelleryMart.repository.CartRepository;
import com.ecommerce.jewelleryMart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart getCartOrThrow(String userId) {
        return cartRepository.findByUserId(userId)
                .filter(c -> c.getItems() != null && !c.getItems().isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("Cart not found or is empty."));
    }

    /**
     * Refactoring: Split Method.
     * The business logic (clearItems) is in the Model.
     * The persistence logic (save) is here in the Service.
     */
    public void clearCart(Cart cart) {
        cart.clearItems(); 
        cartRepository.save(cart);
    }

    /**
     * Refactoring: Move Method.
     * Coordinates between Cart items and Product repository to build detailed views.
     */
    public List<Map<String, Object>> getCartItemsWithDetails(Cart cart) {
        List<CartItem> items = cart.getItems();
        if (items == null || items.isEmpty()) return new ArrayList<>();

        List<String> productIds = items.stream()
                .map(CartItem::getProductId)
                .collect(Collectors.toList());

        List<Product> products = productRepository.findAllById(productIds);
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        List<Map<String, Object>> itemsSummary = new ArrayList<>();

        for (CartItem item : items) {
            Product product = productMap.get(item.getProductId());
            if (product == null) continue;

            double itemTotal = item.getFinalPrice() * item.getQuantity();

            Map<String, Object> summaryItem = new HashMap<>();
            summaryItem.put("productId", item.getProductId());
            summaryItem.put("productName", product.getName());
            summaryItem.put("quantity", item.getQuantity());
            summaryItem.put("grams", item.getGrams());
            summaryItem.put("price", product.getPrice());
            summaryItem.put("finalPrice", item.getFinalPrice());
            summaryItem.put("itemTotal", itemTotal);
            itemsSummary.add(summaryItem);
        }
        return itemsSummary;
    }
}