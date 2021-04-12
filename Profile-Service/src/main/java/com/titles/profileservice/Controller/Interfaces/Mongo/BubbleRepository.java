package com.titles.profileservice.Controller.Interfaces.Mongo;

import com.titles.profileservice.Model.Bubble;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BubbleRepository extends MongoRepository<Bubble, String> { }
