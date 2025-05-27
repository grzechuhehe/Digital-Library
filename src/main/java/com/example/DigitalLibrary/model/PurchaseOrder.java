package com.example.DigitalLibrary.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB document for PurchaseOrders collection.
 */
@Document(collection = "PurchaseOrders")
public class PurchaseOrder extends BaseRecord {
    // Additional fields specific to PurchaseOrder can be added here
}