package com.titles.profileservice;

import com.titles.profileservice.Controller.DBService;
import com.titles.profileservice.Controller.Interfaces.Mongo.BubbleRepository;
import com.titles.profileservice.Controller.Interfaces.Mongo.MemRepository;
import com.titles.profileservice.Controller.Interfaces.Mongo.ProfileRepository;
import com.titles.profileservice.Controller.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//docker build -t profile-service .

@SpringBootApplication
public class ProfileServiceApplication implements CommandLineRunner {

	@Autowired
	private MemRepository memRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private BubbleRepository bubbleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProfileServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		DBService db = DBService.defaultService;
		ProfileService service = ProfileService.defaultService;
		db.init(memRepository, profileRepository, bubbleRepository);
		service.init(db);
	}


}
