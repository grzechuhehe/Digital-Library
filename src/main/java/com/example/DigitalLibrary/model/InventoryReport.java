package com.example.DigitalLibrary.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB document for InventoryReport collection.
 */
@Document(collection = "InventoryReport")
public class InventoryReport extends BaseRecord {
    // Additional fields specific to InventoryReport can be added here
}