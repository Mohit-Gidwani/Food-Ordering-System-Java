package services;

import models.*;
import utils.Database;
import java.util.*;

public class OrderService {
    private Database db;

    public OrderService() {
        this.db = Database.getInstance();
    }

    public List<Food> getAvailableFoods() {
        List<Food> availableFoods = new ArrayList<>();
        for (Food food : db.getAllFoods()) {
            if (food.isAvailable()) {
                availableFoods.add(food);
            }
        }
        return availableFoods;
    }

    public Order createOrder(String userId, String deliveryAddress) {
        String orderId = "ORD" + db.getNextOrderId();
        Order order = new Order(orderId, userId, deliveryAddress);
        return order;
    }

    public void placeOrder(Order order) {
        db.addOrder(order);
    }

    public List<Order> getUserOrderHistory(String userId) {
        return db.getUserOrders(userId);
    }

    public void displayOrderSummary(Order order) {
        System.out.println("\n============================================");
        System.out.println("              ORDER SUMMARY                 ");
        System.out.println("============================================");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Date: " + order.getOrderDate());
        System.out.println("Status: " + order.getStatus());
        System.out.println("Delivery Address: " + order.getDeliveryAddress());
        System.out.println("---------------------------------------------");
        System.out.println("Items:");
        for (Map.Entry<Food, Integer> entry : order.getItems().entrySet()) {
            Food food = entry.getKey();
            int qty = entry.getValue();
            System.out.printf("  %-20s x%d - Rs.%.2f%n", food.getName(), qty, food.getPrice() * qty);
        }
        System.out.println("---------------------------------------------");
        System.out.printf("TOTAL: Rs.%.2f%n", order.getTotalAmount());
        System.out.println("============================================");
    }
}
