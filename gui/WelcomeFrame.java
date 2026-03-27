package gui;

import javax.swing.*;
import java.awt.*;

public class WelcomeFrame extends JFrame {
    
    public WelcomeFrame() {
        setTitle("Welcome - Food Ordering System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        
        initComponents();
        
        Timer timer = new Timer(3000, e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private void initComponents() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gp = new GradientPaint(0, 0, new Color(220, 20, 60), 
                                                     0, getHeight(), new Color(150, 10, 30));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel iconLabel = new JLabel("🍔", SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 100));
        gbc.gridy = 0;
        panel.add(iconLabel, gbc);
        
        JLabel titleLabel = new JLabel("FOODIE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        panel.add(titleLabel, gbc);
        
        JLabel subtitleLabel = new JLabel("Food Ordering System");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        subtitleLabel.setForeground(new Color(255, 200, 200));
        gbc.gridy = 2;
        panel.add(subtitleLabel, gbc);
        
        JLabel loadingLabel = new JLabel("Loading...");
        loadingLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        loadingLabel.setForeground(Color.WHITE);
        gbc.gridy = 3;
        gbc.insets = new Insets(30, 10, 10, 10);
        panel.add(loadingLabel, gbc);
        
        add(panel);
    }
}
