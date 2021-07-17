package ru.job4j.pool;

public class User {
    private final String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{"
                + "username='" + username + '\''
                + ", email='" + email + '\''
                + '}';
    }
}
