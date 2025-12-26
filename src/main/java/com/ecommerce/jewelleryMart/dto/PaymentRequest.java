package com.ecommerce.jewelleryMart.dto;

public class PaymentRequest {
    private String userId;
    private Double discount;
    private DeliveryDetails delivery;

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Double getDiscount() { return discount; }
    public void setDiscount(Double discount) { this.discount = discount; }

    public DeliveryDetails getDelivery() { return delivery; }
    public void setDelivery(DeliveryDetails delivery) { this.delivery = delivery; }
}