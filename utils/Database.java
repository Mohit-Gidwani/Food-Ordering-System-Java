package utils;

import models.*;
import java.util.*;

public class Database {
    private static Database instance;
    private Map<String, Person> users;
    private Map<String, Food> foods;
    private Map<String, Order> orders;
    private int userCounter = 1000;
    private int foodCounter = 100;
    private int orderCounter = 1;

    private Database() {
        users = new HashMap<>();
        foods = new HashMap<>();
        orders = new HashMap<>();
        initializeData();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private void initializeData() {
        // Default admin
        users.put("admin", new Admin("A001", "admin", "admin123", "System Admin", "admin@food.com", "ADM001"));
        
        // Sample users
        users.put("user", new User("U" + (++userCounter), "user", "user123", "John Doe", "user@email.com", "123 Main St", "9876543210"));
        users.put("jane", new User("U" + (++userCounter), "jane", "jane123", "Jane Smith", "jane@email.com", "456 Oak Ave", "9876543211"));

        // Sample food items with icons
        foods.put("F" + (++foodCounter), new Food("F" + foodCounter, "Margherita Pizza", "Pizza", 299, "Classic cheese pizza with tomato sauce", "🍕"));
        foods.put("F" + (++foodCounter), new Food("F" + foodCounter, "Pepperoni Pizza", "Pizza", 399, "Spicy pepperoni with mozzarella", "🍕"));
        foods.put("F" + (++foodCounter), new Food("F" + foodCounter, "Veggie Burger", "Burger", 199, "Grilled veggie patty with fresh veggies", "🍔"));
        foods.put("F" + (++foodCounter), new Food("F" + foodCounter, "Chicken Burger", "Burger", 249, "Crispy chicken with mayo and lettuce", "🍔"));
        foods.put("F" + (++foodCounter), new Food("F" + foodCounter, "French Fries", "Sides", 99, "Crispy golden fries", "🍟"));
        foods.put("F" + (++foodCounter), new Food("F" + foodCounter, "Coca Cola", "Beverages", 49, "Chilled 500ml bottle", "🥤"));
        foods.put("F" + (++foodCounter), new Food("F" + foodCounter, "Chocolate Cake", "Dessert", 149, "Rich chocolate layer cake", "🍰"));
        foods.put("F" + (++foodCounter), new Food("F" + foodCounter, "Ice Cream", "Dessert", 89, "Vanilla ice cream with toppings", "🍨"));
        foods.put("F" + (++foodCounter), new Food("F" + foodCounter, "Chicken Wings", "Sides", 179, "Spicy buffalo wings", "🍗"));
        foods.put("F" + (++foodCounter), new Food("F" + foodCounter, "Soda", "Beverages", 39, "Refreshing soft drink", "🥤"));
    }

    public Person getUser(String username) { return users.get(username); }
    public void addUser(User user) { users.put(user.getUsername(), user); }
    
    public Collection<Food> getAllFoods() { return foods.values(); }
    public Food getFood(String id) { return foods.get(id); }
    public void addFood(Food food) { foods.put(food.getId(), food); }
    public void removeFood(String id) { foods.remove(id); }
    
    public void addOrder(Order order) { orders.put(order.getId(), order); }
    public Collection<Order> getAllOrders() { return orders.values(); }
    public List<Order> getUserOrders(String userId) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getUserId().equals(userId)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }
    public Order getOrder(String id) { return orders.get(id); }
    
    public int getNextUserId() { return ++userCounter; }
    public int getNextFoodId() { return ++foodCounter; }
    public int getNextOrderId() { return ++orderCounter; }
}
