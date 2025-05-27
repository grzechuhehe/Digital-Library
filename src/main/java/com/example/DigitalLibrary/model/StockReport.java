package com.example.DigitalLibrary.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB document for StockReport collection.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "StockReport")
public class StockReport extends BaseRecord {
    // Inherits all fields from BaseRecord
}