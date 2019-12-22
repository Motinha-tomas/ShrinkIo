package com.example.shrinkio.model;

public class User {
    private String username = "";
    private String age = "";
    private String country = "";
    private String ImageUrl;
    private String Id;

    public User() {

    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }



    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getImageUrl() {
        return ImageUrl;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}

