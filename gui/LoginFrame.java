package gui;

import models.Person;
import services.AuthService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private AuthService authService;

    public LoginFrame() {
        authService = new AuthService();
        setTitle("Food Ordering System - Login");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(255, 250, 240));

        JLabel titleLabel = new JLabel("FOOD ORDERING SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(220, 20, 60));
        titleLabel.setBounds(70, 30, 350, 40);
        mainPanel.add(titleLabel);

        JLabel iconLabel = new JLabel("🍔", SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        iconLabel.setBounds(175, 80, 100, 80);
        mainPanel.add(iconLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setBounds(50, 180, 100, 30);
        mainPanel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 180, 230, 35);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setBounds(50, 230, 100, 30);
        mainPanel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 230, 230, 35);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(passwordField);

        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setBounds(150, 290, 230, 45);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setBackground(new Color(200, 16, 46));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);
        loginBtn.setContentAreaFilled(false);
        loginBtn.setOpaque(true);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.addActionListener(e -> login());
        mainPanel.add(loginBtn);

        JButton registerBtn = new JButton("Create New Account");
        registerBtn.setBounds(150, 345, 230, 40);
        registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
        registerBtn.setBackground(new Color(34, 139, 34));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setBorderPainted(false);
        registerBtn.setContentAreaFilled(false);
        registerBtn.setOpaque(true);
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerBtn.addActionListener(e -> openRegister());
        mainPanel.add(registerBtn);

        JLabel hintLabel = new JLabel("<html><center>Default Admin: admin / admin123<br>Default User: user / user123</center></html>");
        hintLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        hintLabel.setForeground(Color.GRAY);
        hintLabel.setBounds(100, 390, 250, 40);
        mainPanel.add(hintLabel);

        add(mainPanel);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Person user = authService.login(username, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Welcome!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            if (user.getRole().equals("ADMIN")) {
                new AdminFrame().setVisible(true);
            } else {
                new UserFrame().setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRegister() {
        new RegisterFrame(this).setVisible(true);
    }
}
