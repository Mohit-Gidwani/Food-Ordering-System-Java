package models;

public class Food {
    private String id;
    private String name;
    private String category;
    private double price;
    private String description;
    private boolean available;
    private String icon;

    public Food(String id, String name, String category, double price, String description, String icon) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.available = true;
        this.icon = icon;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public boolean isAvailable() { return available; }
    public String getIcon() { return icon; }

    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setAvailable(boolean available) { this.available = available; }
    public void setIcon(String icon) { this.icon = icon; }
}
