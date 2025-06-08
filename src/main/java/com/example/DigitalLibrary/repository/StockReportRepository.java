package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.StockReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for StockReport documents.
 */
@Repository
public interface StockReportRepository extends MongoRepository<StockReport, String> {
}