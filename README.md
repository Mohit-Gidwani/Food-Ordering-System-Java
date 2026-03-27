# 🍔 Food Ordering System

A Java-based desktop application for managing food orders with separate Admin and User panels. Built with Java Swing for the GUI and in-memory data storage.

![Java](https://img.shields.io/badge/Java-8+-orange.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)
![Platform](https://img.shields.io/badge/Platform-Windows-blue.svg)

---

## 📋 Table of Contents

- [Features](#features)
- [Screenshots](#screenshots)
- [Installation](#installation)
- [Usage](#usage)
- [Default Credentials](#default-credentials)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [How to Contribute](#how-to-contribute)
- [License](#license)

---

## ✨ Features

### 👤 User Features
- 🔐 Secure login and registration system
- 🍕 Browse food menu with emoji icons
- 🛒 Add items to cart with quantity selection
- 💳 Place orders with order confirmation
- 📦 View order status in real-time

### 👨‍💼 Admin Features
- 📝 Add, edit, and delete food items
- 🖼️ Select food icons from emoji picker
- 📊 View all customer orders
- 🔄 Update order status (Pending → Confirmed → Ready → Delivered)
- 📈 Auto-refresh order list every 3 seconds

---

## 📸 Screenshots

Screenshots are stored in the `Images/` folder.

### Screenshot 1
![Screenshot 1](Images/Screenshot%202026-03-27%20200019.png)

### Screenshot 2
![Screenshot 2](Images/Screenshot%202026-03-27%20200140.png)

### Screenshot 3
![Screenshot 3](Images/Screenshot%202026-03-27%20200219.png)

### Screenshot 4
![Screenshot 4](Images/Screenshot%202026-03-27%20200322.png)

---

## 🚀 Installation

### Prerequisites
- Java JDK 8 or higher installed
- Windows operating system

### Steps

1. **Clone or Download the repository**
   ```bash
   git clone https://github.com/yourusername/food-ordering-system.git
   cd food-ordering-system
   ```

2. **Compile the project**
   - Double-click `COMPILE.bat`
   - OR run via command line:
     ```bash
     javac -encoding UTF-8 -d . models\*.java utils\*.java services\*.java gui\*.java FoodOrderingSystem.java
     ```

3. **Run the application**
   - Double-click `RUN.bat`
   - OR run via command line:
     ```bash
     java FoodOrderingSystem
     ```

---

## 🎮 Usage

### For First-Time Users

1. Run the application using `RUN.bat`
2. Wait for the Welcome Screen (3 seconds)
3. Login with default credentials:
   - **Admin**: `admin` / `admin123`
   - **User**: `user` / `user123`

### User Workflow
1. Login as a user
2. Browse food items with icons
3. Select quantity and click "Add to Cart"
4. Review your cart on the right side
5. Click "PLACE ORDER" to confirm
6. View order confirmation with Order ID

### Admin Workflow
1. Login as admin
2. **Food Items Tab** (Green):
   - Click "+ Add Food" to add new items
   - Select item and click "Edit" to modify
   - Select item and click "Delete" to remove
3. **Orders Tab** (Yellow):
   - View all customer orders (auto-refreshes)
   - Select order and click "Update Status" to change status

---

## 🔑 Default Credentials

| Role  | Username | Password | Access Level |
|-------|----------|----------|--------------|
| Admin | `admin`  | `admin123` | Full access to manage food and orders |
| User  | `user`   | `user123`  | Can browse menu and place orders |

---

## 📁 Project Structure

```
food-ordering-system/
│
├── 📄 COMPILE.bat              # Compile all Java files
├── 📄 RUN.bat                  # Run the application
├── 📄 CLEAN.bat                # Clean compiled files
├── 📄 README.md                # This file
├── 📄 README.txt               # Simple text instructions
│
├── 📂 models/                  # Data Models
│   ├── Person.java             # Abstract base class
│   ├── User.java               # User model
│   ├── Admin.java              # Admin model
│   ├── Food.java               # Food item model
│   └── Order.java              # Order model
│
├── 📂 utils/                   # Utilities
│   └── Database.java           # In-memory data storage
│
├── 📂 services/                # Business Logic
│   ├── AuthService.java        # Login/Register logic
│   ├── OrderService.java       # Order processing
│   └── AdminService.java       # Admin operations
│
├── 📂 gui/                     # User Interface
│   ├── WelcomeFrame.java       # Splash screen
│   ├── LoginFrame.java         # Login/Register UI
│   ├── UserFrame.java          # User panel UI
│   ├── RegisterFrame.java      # Registration dialog
│   └── AdminFrame.java         # Admin panel UI
│
└── 📄 FoodOrderingSystem.java  # Main entry point
```

---

## 🛠️ Technologies Used

- **Java 8+** - Core programming language
- **Java Swing** - GUI framework for desktop application
- **AWT (Abstract Window Toolkit)** - For graphics and event handling
- **Collections Framework** - Data structures (HashMap, ArrayList)

---

## 🎯 Features in Detail

### Splash Screen
- 3-second animated welcome screen
- App branding with food emoji
- Auto-transition to login

### Login System
- Secure authentication
- Role-based access (Admin/User)
- Registration for new users

### Food Menu
- Emoji icons for visual appeal
- Category organization (Pizza, Burger, Sides, etc.)
- Price display
- Availability status

### Shopping Cart
- Real-time cart updates
- Quantity management
- Total amount calculation
- Remove items

### Order Management
- Unique Order IDs
- Status tracking (PENDING → CONFIRMED → PREPARING → READY → DELIVERED)
- Order history
- Auto-refresh for admins

---

## 🤝 How to Contribute

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Ideas for Contributions
- Add database persistence (MySQL/PostgreSQL)
- Add payment gateway integration
- Generate PDF receipts
- Add email notifications
- Implement user profile management
- Add food search and filter functionality

---

**Made with ❤️ and ☕ in Java**
