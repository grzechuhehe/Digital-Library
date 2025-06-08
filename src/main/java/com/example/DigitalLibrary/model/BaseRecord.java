package com.example.DigitalLibrary.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import java.util.Date;

/**
 * Abstract base class for MongoDB documents with common fields.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseRecord {

    @Id
    private ObjectId id;
    @Indexed
    private String fileName;
    private String relativePath;
    private String fullPath;
    private Integer size;
    @Indexed
    private Date uploaded;
    private Binary content;
    /** Extracted text content of the document. */
    @TextIndexed
    private String textContent;
}