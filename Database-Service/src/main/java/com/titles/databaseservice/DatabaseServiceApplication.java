package com.titles.databaseservice;

import com.titles.databaseservice.Controller.DBService;
import com.titles.databaseservice.cold.ColdDBService;
import com.titles.databaseservice.cold.ColdRepository;
import com.titles.databaseservice.Controller.HotDBService;
import com.titles.databaseservice.Controller.Interfacies.HotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = HotRepository.class)
@EnableJpaRepositories(basePackageClasses = ColdRepository.class)
@SpringBootApplication
public class DatabaseServiceApplication implements CommandLineRunner {

	@Autowired
	private HotRepository hotRepository;

	@Autowired
	private ColdRepository coldRepository;


	public static void main(String[] args) {
		SpringApplication.run(DatabaseServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		HotDBService hdbs = HotDBService.defaultService;
		hdbs.init(hotRepository);
		ColdDBService cdbs = ColdDBService.defaultService;
		cdbs.init(coldRepository);
		DBService dbService = DBService.defaultService;
		dbService.init(hdbs,cdbs);

		coldRepository.deleteAll();
	}
}
