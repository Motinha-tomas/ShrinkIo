package com.example.shrinkio.model;

public class User {

    public String country;

    public String city;


    public User(String country, String city) {

        this.country = country;
        this.city = city;

    }

    public User() {

    }



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

