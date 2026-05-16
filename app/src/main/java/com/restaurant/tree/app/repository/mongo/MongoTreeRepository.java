package com.restaurant.tree.app.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.tree.app.document.MongoTreeDocument;

@Repository
public interface MongoTreeRepository extends MongoRepository<MongoTreeDocument, String> {

}