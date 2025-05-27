package com.example.DigitalLibrary.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB document for MonthlyCategory collection.
 */
@Document(collection = "MonthlyCategory")
public class MonthlyCategory extends BaseRecord {
    // Additional fields specific to MonthlyCategory can be added here
}