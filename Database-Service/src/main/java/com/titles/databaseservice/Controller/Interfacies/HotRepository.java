package com.titles.databaseservice.Controller.Interfacies;

import com.titles.databaseservice.Model.HotPaper;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotRepository extends MongoRepository<HotPaper, String> {
//    public HotCustomer findByFirstName(String firstName);
//    public List<HotCustomer> findByLastName(String lastName);
}
