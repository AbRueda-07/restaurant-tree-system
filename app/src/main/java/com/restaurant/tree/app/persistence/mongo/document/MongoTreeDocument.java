package com.restaurant.tree.app.persistence.mongo.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trees")
public class MongoTreeDocument {

    @Id
    private String id;

    private String value;

    private List<MongoTreeDocument> children = new ArrayList<>();

    public MongoTreeDocument() {
    }

    public MongoTreeDocument(String id, String value) {
        this.id = id;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<MongoTreeDocument> getChildren() {
        return children;
    }

    public void setChildren(List<MongoTreeDocument> children) {
        this.children = children;
    }
}
