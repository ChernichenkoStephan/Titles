package com.titles.authservice;

import com.titles.authservice.Controller.AuthService;
import com.titles.authservice.Controller.CustomerRepository;
import com.titles.authservice.Controller.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication implements CommandLineRunner {
	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DBService dbs = DBService.defaultService;
		dbs.init(repository);
		AuthService authService = AuthService.defaultService;
		authService.init(dbs);
	}
}
