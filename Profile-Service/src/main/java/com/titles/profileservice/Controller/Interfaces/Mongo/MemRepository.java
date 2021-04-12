package com.titles.profileservice.Controller.Interfaces.Mongo;

import com.titles.profileservice.Model.MemsPack;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemRepository extends MongoRepository<MemsPack, String> { }