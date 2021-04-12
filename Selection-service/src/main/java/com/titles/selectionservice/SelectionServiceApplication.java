package com.titles.selectionservice;

import com.titles.selectionservice.Controller.DBService;
import com.titles.selectionservice.Controller.Interfacies.SelectionRepository;
import com.titles.selectionservice.Controller.SelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// docker build -t stephanchernichenko/selection-service .

@SpringBootApplication
public class SelectionServiceApplication implements CommandLineRunner {

	@Autowired
	private SelectionRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SelectionServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {

		// Initialization of db service
		DBService db = DBService.defaultService;

		// Connecting our facade to db
		db.init(repository);

		// Initialization of selections service
		SelectionService service = SelectionService.defaultService;

		// Getting backup from db
		if (repository != null) service.init(db.getBackup());

	}

}
