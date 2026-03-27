package gui;

import services.AuthService;
import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JDialog {
    private JTextField usernameField, nameField, emailField, phoneField, addressField;
    private JPasswordField passwordField;
    private AuthService authService;

    public RegisterFrame(JFrame parent) {
        super(parent, "Register New Account", true);
        authService = new AuthService();
        setSize(400, 450);
        setLocationRelativeTo(parent);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 250, 240));

        JLabel titleLabel = new JLabel("CREATE ACCOUNT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(220, 20, 60));
        titleLabel.setBounds(110, 20, 200, 30);
        panel.add(titleLabel);

        int y = 70, h = 25, gap = 35;

        addLabel(panel, "Username:", 30, y);
        usernameField = addField(panel, 130, y, 200);

        y += gap;
        addLabel(panel, "Password:", 30, y);
        passwordField = new JPasswordField();
        passwordField.setBounds(130, y, 200, h);
        panel.add(passwordField);

        y += gap;
        addLabel(panel, "Full Name:", 30, y);
        nameField = addField(panel, 130, y, 200);

        y += gap;
        addLabel(panel, "Email:", 30, y);
        emailField = addField(panel, 130, y, 200);

        y += gap;
        addLabel(panel, "Phone:", 30, y);
        phoneField = addField(panel, 130, y, 200);

        y += gap;
        addLabel(panel, "Address:", 30, y);
        addressField = addField(panel, 130, y, 200);

        JButton registerBtn = new JButton("REGISTER");
        registerBtn.setBounds(130, 320, 200, 45);
        registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
        registerBtn.setBackground(new Color(34, 139, 34));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setBorderPainted(false);
        registerBtn.setContentAreaFilled(false);
        registerBtn.setOpaque(true);
        registerBtn.addActionListener(e -> register());
        panel.add(registerBtn);

        add(panel);
    }

    private void addLabel(JPanel panel, String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setBounds(x, y, 100, 25);
        panel.add(label);
    }

    private JTextField addField(JPanel panel, int x, int y, int w) {
        JTextField field = new JTextField();
        field.setBounds(x, y, w, 25);
        panel.add(field);
        return field;
    }

    private void register() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();

        if (username.isEmpty() || password.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (authService.registerUser(username, password, name, email, address, phone)) {
            JOptionPane.showMessageDialog(this, "Registration successful! Please login.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
