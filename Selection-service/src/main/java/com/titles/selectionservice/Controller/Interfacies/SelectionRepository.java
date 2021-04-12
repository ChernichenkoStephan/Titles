package com.titles.selectionservice.Controller.Interfacies;

import com.titles.selectionservice.Model.Selection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SelectionRepository extends MongoRepository<Selection, String> {
    List<Selection> getByAccountID(Integer accountID);
}
