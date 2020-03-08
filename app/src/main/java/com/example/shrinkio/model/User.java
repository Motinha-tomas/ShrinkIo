package com.example.shrinkio.model;

public class User {
    private String Username;
    private String Age;
    private String Country;
    private String ImageUrl;
    private String Id;


    public User(String username, String age, String country, String ImageUrl, String Id) {
        this.Username = username;
        this.Age = age;
        this.Country = country;
        this.ImageUrl = ImageUrl;
        this.Id = Id;
    }

    public User() {

    }



    public String getUsername() {
        return Username;
    }

    public void setUsername(String username){
        this.Username = username;
    }



    public String getAge() {

        return Age;
    }

    public void setAge(String age) {

        this.Age = age;
    }


    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        this.Country = country;
    }


    public void setImageUrl() {
        this.ImageUrl = ImageUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }
}

