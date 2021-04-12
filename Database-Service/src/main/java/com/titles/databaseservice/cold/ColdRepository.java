package com.titles.databaseservice.cold;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import com.titles.databaseservice.Model.ColdPaper;
import org.springframework.data.repository.CrudRepository;


public interface ColdRepository extends CrudRepository<ColdPaper, Long> {
}