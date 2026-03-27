package gui;

import models.*;
import services.*;
import utils.Database;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class UserFrame extends JFrame {
    private OrderService orderService;
    private DefaultTableModel menuTableModel;
    private DefaultTableModel cartTableModel;
    private JLabel totalLabel;
    private Order currentOrder;
    private Map<String, Food> foodMap;
    private Map<Food, Integer> cart;

    public UserFrame() {
        orderService = new OrderService();
        foodMap = new HashMap<>();
        cart = new HashMap<>();
        Person user = AuthService.getCurrentUser();
        currentOrder = new Order("ORD" + System.currentTimeMillis(), user.getId(), user instanceof User ? ((User)user).getAddress() : "");

        setTitle("Food Ordering System - User Panel");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        loadFoodItems();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(220, 20, 60));
        headerPanel.setPreferredSize(new Dimension(0, 60));

        JLabel titleLabel = new JLabel("🍕 FOOD MENU", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(Color.WHITE);
        logoutBtn.setForeground(new Color(200, 16, 46));
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 12));
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setOpaque(true);
        logoutBtn.setPreferredSize(new Dimension(80, 35));
        logoutBtn.addActionListener(e -> logout());
        headerPanel.add(logoutBtn, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(550);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Available Food Items"));

        String[] menuColumns = {"Icon", "ID", "Name", "Category", "Price (Rs.)", "Description"};
        menuTableModel = new DefaultTableModel(menuColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable menuTable = new JTable(menuTableModel);
        menuTable.setRowHeight(35);
        menuTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        menuTable.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                setText(value != null ? value.toString() : "");
                setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        });
        menuTable.setFont(new Font("Arial", Font.PLAIN, 12));

        leftPanel.add(new JScrollPane(menuTable), BorderLayout.CENTER);

        JPanel addPanel = new JPanel(new FlowLayout());
        JTextField qtyField = new JTextField("1", 5);
        JButton addToCartBtn = new JButton("Add to Cart");
        addToCartBtn.setBackground(new Color(34, 139, 34));
        addToCartBtn.setForeground(Color.WHITE);
        addToCartBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addToCartBtn.setPreferredSize(new Dimension(130, 40));
        addToCartBtn.setFocusPainted(false);
        addToCartBtn.setBorderPainted(false);
        addToCartBtn.setContentAreaFilled(false);
        addToCartBtn.setOpaque(true);
        addToCartBtn.addActionListener(e -> {
            int selectedRow = menuTable.getSelectedRow();
            if (selectedRow >= 0) {
                String foodId = (String) menuTableModel.getValueAt(selectedRow, 1);
                Food food = foodMap.get(foodId);
                int qty = Integer.parseInt(qtyField.getText());
                if (qty > 0) {
                    cart.put(food, cart.getOrDefault(food, 0) + qty);
                    updateCartTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a food item!");
            }
        });
        addPanel.add(new JLabel("Qty:"));
        addPanel.add(qtyField);
        addPanel.add(addToCartBtn);
        leftPanel.add(addPanel, BorderLayout.SOUTH);

        splitPane.setLeftComponent(leftPanel);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Your Cart"));

        String[] cartColumns = {"Item", "Qty", "Price", "Total"};
        cartTableModel = new DefaultTableModel(cartColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable cartTable = new JTable(cartTableModel);
        cartTable.setRowHeight(25);
        rightPanel.add(new JScrollPane(cartTable), BorderLayout.CENTER);

        JPanel cartBottomPanel = new JPanel(new BorderLayout());
        totalLabel = new JLabel("Total: Rs. 0.00", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(new Color(220, 20, 60));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cartBottomPanel.add(totalLabel, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JButton removeBtn = new JButton("Remove Selected");
        removeBtn.setBackground(new Color(255, 140, 0));
        removeBtn.setForeground(Color.WHITE);
        removeBtn.setFont(new Font("Arial", Font.BOLD, 13));
        removeBtn.setFocusPainted(false);
        removeBtn.setBorderPainted(false);
        removeBtn.setContentAreaFilled(false);
        removeBtn.setOpaque(true);
        removeBtn.addActionListener(e -> {
            int row = cartTable.getSelectedRow();
            if (row >= 0) {
                String itemName = (String) cartTableModel.getValueAt(row, 0);
                cart.entrySet().removeIf(entry -> entry.getKey().getName().equals(itemName));
                updateCartTable();
            }
        });

        JButton checkoutBtn = new JButton("PLACE ORDER");
        checkoutBtn.setBackground(new Color(200, 16, 46));
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setFont(new Font("Arial", Font.BOLD, 16));
        checkoutBtn.setPreferredSize(new Dimension(180, 45));
        checkoutBtn.setFocusPainted(false);
        checkoutBtn.setBorderPainted(false);
        checkoutBtn.setContentAreaFilled(false);
        checkoutBtn.setOpaque(true);
        checkoutBtn.addActionListener(e -> checkout());

        btnPanel.add(removeBtn);
        btnPanel.add(checkoutBtn);
        cartBottomPanel.add(btnPanel, BorderLayout.SOUTH);

        rightPanel.add(cartBottomPanel, BorderLayout.SOUTH);
        splitPane.setRightComponent(rightPanel);

        add(splitPane, BorderLayout.CENTER);
    }

    private void loadFoodItems() {
        menuTableModel.setRowCount(0);
        foodMap.clear();
        List<Food> foods = orderService.getAvailableFoods();
        for (Food food : foods) {
            foodMap.put(food.getId(), food);
            menuTableModel.addRow(new Object[]{
                food.getIcon(),
                food.getId(),
                food.getName(),
                food.getCategory(),
                String.format("%.2f", food.getPrice()),
                food.getDescription()
            });
        }
    }

    private void updateCartTable() {
        cartTableModel.setRowCount(0);
        double total = 0;
        for (Map.Entry<Food, Integer> entry : cart.entrySet()) {
            Food food = entry.getKey();
            int qty = entry.getValue();
            double itemTotal = food.getPrice() * qty;
            total += itemTotal;
            cartTableModel.addRow(new Object[]{
                food.getName(),
                qty,
                String.format("%.2f", food.getPrice()),
                String.format("%.2f", itemTotal)
            });
        }
        totalLabel.setText("Total: Rs. " + String.format("%.2f", total));
    }

    private void checkout() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Map.Entry<Food, Integer> entry : cart.entrySet()) {
            currentOrder.addItem(entry.getKey(), entry.getValue());
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Total Amount: Rs. " + String.format("%.2f", currentOrder.getTotalAmount()) + "\nConfirm order?",
            "Confirm Order", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            orderService.placeOrder(currentOrder);
            JOptionPane.showMessageDialog(this, "Order placed successfully!\nOrder ID: " + currentOrder.getId());
            cart.clear();
            updateCartTable();
            Person user = AuthService.getCurrentUser();
            currentOrder = new Order("ORD" + System.currentTimeMillis(), user.getId(), user instanceof User ? ((User)user).getAddress() : "");
        }
    }

    private void logout() {
        AuthService.logout();
        dispose();
        new LoginFrame().setVisible(true);
    }
}
