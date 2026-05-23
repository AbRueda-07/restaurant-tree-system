package com.restaurant.tree.app.persistence.mongo.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.tree.app.persistence.mongo.document.MongoTreeDocument;

@Repository
@Profile("mongo")
public interface MongoTreeRepository extends MongoRepository<MongoTreeDocument, String> {
}
