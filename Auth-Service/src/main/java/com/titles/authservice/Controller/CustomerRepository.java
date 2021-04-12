package com.titles.authservice.Controller;

import java.util.List;

import com.titles.authservice.Model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    public List<Customer> findByLogin(String login);
    public List<Customer> findByPassword(String password);
    public List<Customer> findByLoginAndPassword(String login, String password);
}
