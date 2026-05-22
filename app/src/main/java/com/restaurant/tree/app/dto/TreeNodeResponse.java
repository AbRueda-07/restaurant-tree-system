package com.restaurant.tree.app.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO profesional para la respuesta HTTP.
 * No tiene relación hacia el padre, evitando la recursión infinita en Jackson/Swagger.
 */
public class TreeNodeResponse {

    private Long id;
    private String value;
    private List<TreeNodeResponse> children = new ArrayList<>();

    public TreeNodeResponse() {
    }

    public TreeNodeResponse(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    // Getters y Setters
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

    public List<TreeNodeResponse> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNodeResponse> children) {
        this.children = children;
    }

    public void addChild(TreeNodeResponse child) {
        this.children.add(child);
    }
}