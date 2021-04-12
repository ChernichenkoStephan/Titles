package com.titles.authservice.Controller;

import com.titles.authservice.Model.Customer;

import java.util.List;

public class DBService implements  DataBase{
    private DBService(){}
    public static DBService defaultService = new DBService();
    private CustomerRepository repository;
    public void init(CustomerRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Customer> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public List<Customer> findByPassword(String password) {
        return repository.findByPassword(password);
    }

    @Override
    public List<Customer> findByLoginAndPassword(String login, String password) {
        return repository.findByLoginAndPassword(login,password);
    }
    public void save(Customer customer) {
        repository.save(customer);
    }
    public List<Customer> findAll()
    {
        return repository.findAll();
    }
    public void deleteById(String id)
    {
        repository.deleteById(id);
    }
}
