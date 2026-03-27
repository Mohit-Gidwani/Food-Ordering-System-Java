package models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private String id;
    private String userId;
    private Map<Food, Integer> items;
    private double totalAmount;
    private String status;
    private Date orderDate;
    private String deliveryAddress;

    public Order(String id, String userId, String deliveryAddress) {
        this.id = id;
        this.userId = userId;
        this.items = new HashMap<>();
        this.totalAmount = 0;
        this.status = "PENDING";
        this.orderDate = new Date();
        this.deliveryAddress = deliveryAddress;
    }

    public void addItem(Food food, int quantity) {
        items.put(food, items.getOrDefault(food, 0) + quantity);
        calculateTotal();
    }

    public void calculateTotal() {
        totalAmount = 0;
        for (Map.Entry<Food, Integer> entry : items.entrySet()) {
            totalAmount += entry.getKey().getPrice() * entry.getValue();
        }
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public Map<Food, Integer> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public Date getOrderDate() { return orderDate; }
    public String getDeliveryAddress() { return deliveryAddress; }

    public void setStatus(String status) { this.status = status; }
}
