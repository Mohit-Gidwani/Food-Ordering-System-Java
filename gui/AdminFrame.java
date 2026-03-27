package gui;

import models.*;
import services.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminFrame extends JFrame {
    private AdminService adminService;
    private DefaultTableModel foodTableModel;
    private DefaultTableModel orderTableModel;
    private JTable foodTable;
    private JTabbedPane tabbedPane;
    private Timer refreshTimer;

    public AdminFrame() {
        adminService = new AdminService();
        setTitle("Food Ordering System - Admin Panel");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        loadData();
    }

    private void initComponents() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(220, 20, 60));
        headerPanel.setPreferredSize(new Dimension(0, 60));

        JLabel titleLabel = new JLabel("ADMIN PANEL", SwingConstants.CENTER);
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

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        // Set tab colors before adding tabs
        UIManager.put("TabbedPane.selected", new Color(34, 139, 34));
        UIManager.put("TabbedPane.background", new Color(200, 200, 200));
        UIManager.put("TabbedPane.foreground", Color.WHITE);
        UIManager.put("TabbedPane.selectedForeground", Color.WHITE);

        JPanel foodPanel = createFoodPanel();
        JPanel ordersPanel = createOrdersPanel();

        tabbedPane.addTab("Food Items", foodPanel);
        tabbedPane.addTab("Orders", ordersPanel);

        // Custom tab coloring
        tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
                Graphics2D g2d = (Graphics2D) g;
                if (tabIndex == 0) {
                    g2d.setColor(isSelected ? new Color(34, 139, 34) : new Color(100, 180, 100));
                } else {
                    g2d.setColor(isSelected ? new Color(255, 193, 7) : new Color(255, 220, 100));
                }
                g2d.fillRect(x, y, w, h);
            }
            
            @Override
            protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
                g.setColor(Color.DARK_GRAY);
                g.drawRect(x, y, w, h);
            }
            
            @Override
            protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
                g.setColor(Color.WHITE);
                g.setFont(font);
                g.drawString(title, textRect.x, textRect.y + metrics.getAscent());
            }
        });

        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 1) {
                loadOrders();
            }
        });

        refreshTimer = new Timer(3000, e -> loadOrders());
        refreshTimer.start();

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createFoodPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {"Icon", "ID", "Name", "Category", "Price", "Description", "Available"};
        foodTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        foodTable = new JTable(foodTableModel);
        foodTable.setRowHeight(35);
        foodTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        foodTable.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                setText(value != null ? value.toString() : "");
                setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        });
        foodTable.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(new JScrollPane(foodTable), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        btnPanel.setBackground(new Color(245, 245, 245));

        JButton addBtn = new JButton("+ Add Food");
        addBtn.setBackground(new Color(34, 139, 34));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Arial", Font.BOLD, 13));
        addBtn.setPreferredSize(new Dimension(120, 35));
        addBtn.setFocusPainted(false);
        addBtn.setBorderPainted(false);
        addBtn.setContentAreaFilled(false);
        addBtn.setOpaque(true);
        addBtn.addActionListener(e -> addFood());

        JButton editBtn = new JButton("Edit");
        editBtn.setBackground(new Color(255, 140, 0));
        editBtn.setForeground(Color.WHITE);
        editBtn.setFont(new Font("Arial", Font.BOLD, 13));
        editBtn.setPreferredSize(new Dimension(100, 35));
        editBtn.setFocusPainted(false);
        editBtn.setBorderPainted(false);
        editBtn.setContentAreaFilled(false);
        editBtn.setOpaque(true);
        editBtn.addActionListener(e -> editFood());

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBackground(new Color(200, 30, 30));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 13));
        deleteBtn.setPreferredSize(new Dimension(110, 35));
        deleteBtn.setFocusPainted(false);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setContentAreaFilled(false);
        deleteBtn.setOpaque(true);
        deleteBtn.addActionListener(e -> deleteFood());

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBackground(new Color(70, 130, 180));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFont(new Font("Arial", Font.BOLD, 13));
        refreshBtn.setPreferredSize(new Dimension(110, 35));
        refreshBtn.setFocusPainted(false);
        refreshBtn.setBorderPainted(false);
        refreshBtn.setContentAreaFilled(false);
        refreshBtn.setOpaque(true);
        refreshBtn.addActionListener(e -> loadFoodItems());

        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(refreshBtn);

        panel.add(btnPanel, BorderLayout.NORTH);
        return panel;
    }

    private JPanel createOrdersPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {"Order ID", "User ID", "Total", "Status", "Date"};
        orderTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable orderTable = new JTable(orderTableModel);
        orderTable.setRowHeight(25);
        panel.add(new JScrollPane(orderTable), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        btnPanel.setBackground(new Color(245, 245, 245));

        JButton statusBtn = new JButton("Update Status");
        statusBtn.setBackground(new Color(30, 144, 255));
        statusBtn.setForeground(Color.WHITE);
        statusBtn.setFont(new Font("Arial", Font.BOLD, 13));
        statusBtn.setPreferredSize(new Dimension(140, 35));
        statusBtn.setFocusPainted(false);
        statusBtn.setBorderPainted(false);
        statusBtn.setContentAreaFilled(false);
        statusBtn.setOpaque(true);
        statusBtn.addActionListener(e -> {
            int row = orderTable.getSelectedRow();
            if (row >= 0) {
                String orderId = (String) orderTableModel.getValueAt(row, 0);
                String[] statuses = {"PENDING", "CONFIRMED", "PREPARING", "READY", "DELIVERED", "CANCELLED"};
                String newStatus = (String) JOptionPane.showInputDialog(this, "Select Status:",
                    "Update Order Status", JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);
                if (newStatus != null) {
                    adminService.updateOrderStatus(orderId, newStatus);
                    loadOrders();
                }
            }
        });

        JButton refreshBtn2 = new JButton("Refresh");
        refreshBtn2.setBackground(new Color(70, 130, 180));
        refreshBtn2.setForeground(Color.WHITE);
        refreshBtn2.setFont(new Font("Arial", Font.BOLD, 13));
        refreshBtn2.setPreferredSize(new Dimension(110, 35));
        refreshBtn2.setFocusPainted(false);
        refreshBtn2.setBorderPainted(false);
        refreshBtn2.setContentAreaFilled(false);
        refreshBtn2.setOpaque(true);
        refreshBtn2.addActionListener(e -> loadOrders());

        btnPanel.add(statusBtn);
        btnPanel.add(refreshBtn2);

        panel.add(btnPanel, BorderLayout.NORTH);
        return panel;
    }

    private void loadData() {
        loadFoodItems();
        loadOrders();
    }

    private void loadFoodItems() {
        foodTableModel.setRowCount(0);
        for (Food food : adminService.getAllFoodItems()) {
            foodTableModel.addRow(new Object[]{
                food.getIcon(),
                food.getId(),
                food.getName(),
                food.getCategory(),
                String.format("%.2f", food.getPrice()),
                food.getDescription(),
                food.isAvailable() ? "Yes" : "No"
            });
        }
    }

    private void loadOrders() {
        orderTableModel.setRowCount(0);
        for (Order order : adminService.getAllOrders()) {
            orderTableModel.addRow(new Object[]{
                order.getId(),
                order.getUserId(),
                String.format("%.2f", order.getTotalAmount()),
                order.getStatus(),
                order.getOrderDate().toString()
            });
        }
    }

    private void addFood() {
        JTextField nameField = new JTextField();
        JTextField catField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField descField = new JTextField();
        String[] icons = {"🍕", "🍔", "🍟", "🥤", "🍰", "🍨", "🍗", "🌭", "🍝", "🥗", "🍩", "☕"};
        JComboBox<String> iconCombo = new JComboBox<>(icons);
        iconCombo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));

        Object[] message = {
            "Name:", nameField,
            "Category:", catField,
            "Price:", priceField,
            "Description:", descField,
            "Icon:", iconCombo
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Food Item", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String cat = catField.getText();
                double price = Double.parseDouble(priceField.getText());
                String desc = descField.getText();
                String icon = (String) iconCombo.getSelectedItem();
                adminService.addFoodItem(name, cat, price, desc, icon);
                loadFoodItems();
                JOptionPane.showMessageDialog(this, "Food item added!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editFood() {
        int row = foodTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a food item!");
            return;
        }

        String id = (String) foodTableModel.getValueAt(row, 0);
        String name = (String) foodTableModel.getValueAt(row, 1);
        String cat = (String) foodTableModel.getValueAt(row, 2);
        String price = (String) foodTableModel.getValueAt(row, 3);
        String desc = (String) foodTableModel.getValueAt(row, 4);

        JTextField nameField = new JTextField(name);
        JTextField catField = new JTextField(cat);
        JTextField priceField = new JTextField(price);
        JTextField descField = new JTextField(desc);

        Object[] message = {
            "Name:", nameField,
            "Category:", catField,
            "Price:", priceField,
            "Description:", descField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Edit Food Item", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            adminService.updateFoodItem(id, nameField.getText(), catField.getText(),
                Double.parseDouble(priceField.getText()), descField.getText(), null);
            loadFoodItems();
        }
    }

    private void deleteFood() {
        int row = foodTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a food item!");
            return;
        }
        String id = (String) foodTableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Delete this item?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            adminService.removeFoodItem(id);
            loadFoodItems();
        }
    }

    private void logout() {
        refreshTimer.stop();
        AuthService.logout();
        dispose();
        new LoginFrame().setVisible(true);
    }
}
