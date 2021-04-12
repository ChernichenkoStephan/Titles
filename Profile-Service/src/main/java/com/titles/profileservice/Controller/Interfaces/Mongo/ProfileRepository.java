package com.titles.profileservice.Controller.Interfaces.Mongo;

import com.titles.profileservice.Model.DBProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProfileRepository extends MongoRepository<DBProfile, String> {
    List<DBProfile> getByAccountID(Integer id);
}
