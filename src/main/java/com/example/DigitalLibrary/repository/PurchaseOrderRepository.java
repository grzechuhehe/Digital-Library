package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.PurchaseOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for PurchaseOrder documents.
 */
@Repository
public interface PurchaseOrderRepository extends MongoRepository<PurchaseOrder, String> {
}