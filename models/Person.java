package models;

public abstract class Person {
    protected String id;
    protected String username;
    protected String password;
    protected String name;
    protected String email;

    public Person(String id, String username, String password, String name, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public abstract String getRole();
}
