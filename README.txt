========================================
    FOOD ORDERING SYSTEM
========================================

A Java-based GUI application for food ordering with Admin and User panels.

REQUIREMENTS:
- Java JDK 8 or higher installed
- Windows operating system

HOW TO RUN:

Method 1 - Easy (Recommended):
1. Double-click on RUN.bat
   - If the program is not compiled, it will ask you to run COMPILE.bat first

Method 2 - Compile First:
1. Double-click on COMPILE.bat to compile all Java files
2. Then double-click on RUN.bat to start the application

DEFAULT LOGIN CREDENTIALS:
--------------------------------
| Role  | Username | Password  |
--------------------------------
| Admin | admin    | admin123  |
| User  | user     | user123   |
--------------------------------

FEATURES:
- Login/Registration system
- Admin Panel:
  * Manage food items (Add, Edit, Delete)
  * View all orders
  * Update order status
- User Panel:
  * Browse food menu with icons
  * Add items to cart
  * Place orders

FOLDERS:
- models/     : Data models (User, Admin, Food, Order)
- utils/      : Database and utilities
- services/   : Business logic (Auth, Order, Admin)
- gui/        : User interface screens

BATCH FILES:
- COMPILE.bat : Compiles all Java source files
- RUN.bat     : Runs the application
- CLEAN.bat   : Removes all compiled class files

TROUBLESHOOTING:
- If you get "javac not found" error, make sure Java is installed and added to PATH
- If compilation fails, try running CLEAN.bat first, then COMPILE.bat

Enjoy using Food Ordering System!
