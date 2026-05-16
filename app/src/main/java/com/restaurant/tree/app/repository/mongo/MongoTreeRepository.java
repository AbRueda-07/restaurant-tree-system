package com.restaurant.tree.app.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoTreeRepository extends MongoRepository<MongoTreeDocument, Long> {

}