package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.InventoryReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for InventoryReport documents.
 */
@Repository
public interface InventoryReportRepository extends MongoRepository<InventoryReport, String> {
}