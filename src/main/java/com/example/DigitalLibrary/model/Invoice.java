package com.example.DigitalLibrary.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB document for Invoices collection.
 */
@Document(collection = "Invoices")
public class Invoice extends BaseRecord {
    // Additional fields specific to Invoice can be added here
}