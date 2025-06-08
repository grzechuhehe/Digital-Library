package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.Monthly;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Monthly documents.
 */
@Repository
public interface MonthlyRepository extends MongoRepository<Monthly, String> {
}