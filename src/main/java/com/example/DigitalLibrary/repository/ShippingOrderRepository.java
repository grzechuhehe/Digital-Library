package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.ShippingOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for ShippingOrder documents.
 */
@Repository
public interface ShippingOrderRepository extends MongoRepository<ShippingOrder, String> {
}