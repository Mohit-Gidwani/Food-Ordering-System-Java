package services;

import models.*;
import utils.Database;
import java.util.*;

public class AdminService {
    private Database db;

    public AdminService() {
        this.db = Database.getInstance();
    }

    public void addFoodItem(String name, String category, double price, String description, String icon) {
        String id = "F" + db.getNextFoodId();
        Food food = new Food(id, name, category, price, description, icon);
        db.addFood(food);
    }

    public void removeFoodItem(String id) {
        db.removeFood(id);
    }

    public void updateFoodItem(String id, String name, String category, Double price, String description, Boolean available) {
        Food food = db.getFood(id);
        if (food != null) {
            if (name != null) food.setName(name);
            if (category != null) food.setCategory(category);
            if (price != null) food.setPrice(price);
            if (description != null) food.setDescription(description);
            if (available != null) food.setAvailable(available);
        }
    }

    public List<Food> getAllFoodItems() {
        return new ArrayList<>(db.getAllFoods());
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(db.getAllOrders());
    }

    public void updateOrderStatus(String orderId, String status) {
        Order order = db.getOrder(orderId);
        if (order != null) {
            order.setStatus(status);
        }
    }

    public void displayFoodReport() {
        System.out.println("\n============= FOOD MENU REPORT =============");
        for (Food food : db.getAllFoods()) {
            System.out.printf("ID: %s | Name: %-20s | Category: %-10s | Price: Rs.%.2f | Status: %s%n",
                food.getId(), food.getName(), food.getCategory(), food.getPrice(),
                food.isAvailable() ? "Available" : "Not Available");
        }
        System.out.println("============================================");
    }

    public void displayOrderReport() {
        System.out.println("\n============= ALL ORDERS REPORT =============");
        for (Order order : db.getAllOrders()) {
            System.out.printf("Order ID: %s | User: %s | Amount: Rs.%.2f | Status: %s | Date: %s%n",
                order.getId(), order.getUserId(), order.getTotalAmount(),
                order.getStatus(), order.getOrderDate());
        }
        System.out.println("============================================");
    }
}
