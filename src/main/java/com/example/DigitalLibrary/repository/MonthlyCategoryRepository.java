package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.MonthlyCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for MonthlyCategory documents.
 */
@Repository
public interface MonthlyCategoryRepository extends MongoRepository<MonthlyCategory, String> {
}