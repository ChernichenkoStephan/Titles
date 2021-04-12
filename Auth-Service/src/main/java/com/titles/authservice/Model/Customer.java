package com.titles.authservice.Model;

import org.springframework.data.annotation.Id;


public class Customer {

    @Id
    public String id;
    public Integer userID;
    public String login;
    public String password;


    public Customer() {}

    public Customer(Integer userID, String login, String password) {
        this.userID = userID;
        this.login = login;
        this.password = password;
    }
    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, login='%s', password='%s']",
                id, login, password);
    }

}

