package com.titles.authservice.Controller;

import com.titles.authservice.Model.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AuthService {
    private AuthService(){}
    public static AuthService defaultService = new AuthService();
    private DataBase db;
    public void init(DataBase db){
        this.db = db;
    }

    public JSONObject loginRequest( String login, String password) {
        boolean flag = false;
        JSONObject userJSON = new JSONObject();
        Customer user = new Customer();
        for (Customer customer : db.findByLogin(login)) {
            user = customer;
            flag = true;
        }
        if(!flag)
        {
            userJSON.put("id", null);
            userJSON.put("status", 0);
            userJSON.put("message", "Login is incorrect");
            return userJSON;
        }
        if(!user.getPassword().equals(password))
        {
            userJSON.put("id", null);
            userJSON.put("status", 1);
            userJSON.put("message", "Password is incorrect");
            return userJSON;
        }

        userJSON.put("id", user.getUserID());
        userJSON.put("status", 2);
        userJSON.put("message", "Login success");
        return userJSON;
    }

    public JSONObject registrationRequest(String login, String password) {
        boolean flag = false;
        JSONObject userJSON = new JSONObject();
        for (Customer customer : db.findByLogin(login)) {
            flag = true;
        }
        if(flag)
        {
            userJSON.put("id", null);
            userJSON.put("status", 0);
            userJSON.put("message", "User exists");
            return userJSON;
        }
        Integer userID = db.findAll().size() + 1;
        db.save(new Customer(userID ,login, password));
        userJSON.put("id", userID);
        userJSON.put("status", 1);
        userJSON.put("message", "Registration success");
        return userJSON;
    }

    public JSONObject usersRequest(String key) {
        JSONObject error = new JSONObject();
        JSONObject users = new JSONObject();
        JSONArray userJSON = new JSONArray();
        if(!key.equals("228"))
        {
            error.put("id",null);
            error.put("login",null);
            error.put("password",null);
            error.put("status",0);
            error.put("message","Access is denied");
            return error;
        }
        for (Customer customer : db.findAll()) {
            JSONObject arrJSON = new JSONObject();
            arrJSON.put("id",customer.getUserID());
            arrJSON.put("login",customer.getLogin());
            arrJSON.put("password",customer.getPassword());
            userJSON.add(arrJSON);
        }
        users.put("users",userJSON);
        users.put("status",1);
        users.put("message","Success");
        return users;
    }

    public JSONObject putRequest(String login, String password, String newlogin, String newpassword) {
        JSONObject userJSON = new JSONObject();
        Customer user = new Customer();
        Customer userForDelete = new Customer();
        boolean flag = false;
        for (Customer customer : db.findByLogin(login)) {
            user = customer;
            userForDelete = user;
            flag = true;
        }
        if(!flag)
        {
            userJSON.put("id", null);
            userJSON.put("status", 0);
            userJSON.put("message", "User not found");
            return userJSON;
        }
        if(!user.getPassword().equals(password))
        {
            userJSON.put("id", null);
            userJSON.put("status", 1);
            userJSON.put("message", "Password is incorrect");
            return userJSON;
        }
        if((newlogin!=null)&&(newpassword!=null))
        {
            user = new Customer(newlogin,newpassword);
        }
        else if((newlogin==null)&&(newpassword!=null))
        {
            user = new Customer(login,newpassword);
        }
        else if((newlogin!=null)&&(newpassword==null))
        {
            user = new Customer(newlogin,password);
        }
        user.setId(userForDelete.getId());
        user.setUserID(userForDelete.getUserID());
        db.deleteById(userForDelete.getId());
        db.save(user);
        userJSON.put("status", 2);
        userJSON.put("message", "Success");
        return userJSON;
    }

    public JSONObject check()  {
        JSONObject resp = new JSONObject();
        resp.put("code", 1);
        resp.put("message", "Working right, everything is fine");
        return resp;
    }
}
