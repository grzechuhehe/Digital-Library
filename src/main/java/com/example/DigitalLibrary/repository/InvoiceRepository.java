package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Invoice documents.
 */
@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {
}