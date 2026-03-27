package models;

public class Admin extends Person {
    private String adminCode;

    public Admin(String id, String username, String password, String name, String email, String adminCode) {
        super(id, username, password, name, email);
        this.adminCode = adminCode;
    }

    public String getAdminCode() { return adminCode; }

    @Override
    public String getRole() { return "ADMIN"; }
}
