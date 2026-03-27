package models;

public class User extends Person {
    private String address;
    private String phone;

    public User(String id, String username, String password, String name, String email, String address, String phone) {
        super(id, username, password, name, email);
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() { return address; }
    public String getPhone() { return phone; }

    @Override
    public String getRole() { return "USER"; }
}
