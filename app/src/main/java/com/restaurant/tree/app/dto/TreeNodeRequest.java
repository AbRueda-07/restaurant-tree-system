package com.restaurant.tree.app.dto;

public class TreeNodeRequest {

    private Long id;
    private String value;

    public TreeNodeRequest() {
    }

    public TreeNodeRequest(Long id, String value) {
        this.id = id;
        this.value = value;
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
}