package com.ecommerce.jewelleryMart.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carts")
public class Cart {

    @Id
    private String id;
    private String userId;

    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(String userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
    }

    // Refactoring: Logic to clear items stays in the domain model
    public void clearItems() {
        this.items = new ArrayList<>();
    }

    // Refactoring: Calculation logic stays in the domain model
    public double calculateTotal() {
        if (this.items == null || this.items.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        for (CartItem item : this.items) {
            total += item.getFinalPrice() * item.getQuantity();
        }
        return total;
    }

    // --- Getters and Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
}