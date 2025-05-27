package com.example.DigitalLibrary.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB document for ShippingOrders collection.
 */
@Document(collection = "ShippingOrders")
public class ShippingOrder extends BaseRecord {
    // Additional fields specific to ShippingOrder can be added here
}