package com.restaurant.tree.app.repository.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trees")
public class MongoTreeDocument {

    @Id
    private Long id;

    private String value;

    private List<MongoTreeDocument> children = new ArrayList<>();

    public MongoTreeDocument() {
    }

    public MongoTreeDocument(Long id, String value) {
        this.id = id;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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