package com.example.DigitalLibrary.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB document for Monthly collection.
 */
@Document(collection = "Monthly")
public class Monthly extends BaseRecord {
    // Additional fields specific to Monthly can be added here
}