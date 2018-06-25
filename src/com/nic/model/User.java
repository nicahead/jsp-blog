package com.nic.model;

public class User {
    private int id;
    private int username;
    private int password;

    public User() {
    }

    public User(int username, int password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
