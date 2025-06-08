package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.BaseRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends MongoRepository<BaseRecord, String> {
    // Dodatkowe metody zapytań można dodać tutaj
}
