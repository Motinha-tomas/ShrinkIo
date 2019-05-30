package com.example.shrinkio.model;

public class user {
    private String id;
    private String username;

    public user(String id, String name) {
        this.id = id;
        this.username = name;

    }

    public user() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

