package com.ecommerce.jewelleryMart.service;

import com.ecommerce.jewelleryMart.dto.DeliveryDetails;
import com.ecommerce.jewelleryMart.model.Cart;
import com.ecommerce.jewelleryMart.model.CartItem;
import com.ecommerce.jewelleryMart.model.Order;
import com.ecommerce.jewelleryMart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createAndSaveOrder(String userId, Cart cart, double paidAmount, DeliveryDetails delivery) {
        // Map CartItems to the Order's structure
        List<String> orderProductIds = cart.getItems().stream()
                .map(CartItem::getProductId)
                .collect(Collectors.toList());

        List<Integer> orderQuantities = cart.getItems().stream()
                .map(CartItem::getQuantity)
                .collect(Collectors.toList());

        List<Double> orderGrams = cart.getItems().stream()
                .map(CartItem::getGrams)
                .collect(Collectors.toList());

        Order order = new Order(userId, orderProductIds, orderQuantities, paidAmount);
        order.setGrams(orderGrams);

        if (delivery != null) {
            order.setDeliveryName(delivery.getName());
            order.setDeliveryContact(delivery.getContact());
            order.setDeliveryAddress(delivery.getAddress());
            order.setDeliveryCity(delivery.getCity());
        }

        return orderRepository.save(order);
    }
}