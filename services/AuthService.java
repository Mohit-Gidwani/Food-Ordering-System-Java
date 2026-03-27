package services;

import models.*;
import utils.Database;

public class AuthService {
    private Database db;
    private static Person currentUser;

    public AuthService() {
        this.db = Database.getInstance();
    }

    public Person login(String username, String password) {
        Person user = db.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return user;
        }
        return null;
    }

    public boolean registerUser(String username, String password, String name, String email, String address, String phone) {
        if (db.getUser(username) != null) {
            return false;
        }
        String id = "U" + db.getNextUserId();
        User newUser = new User(id, username, password, name, email, address, phone);
        db.addUser(newUser);
        return true;
    }

    public static void logout() {
        currentUser = null;
    }

    public static Person getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static boolean isAdmin() {
        return currentUser != null && currentUser.getRole().equals("ADMIN");
    }
}
