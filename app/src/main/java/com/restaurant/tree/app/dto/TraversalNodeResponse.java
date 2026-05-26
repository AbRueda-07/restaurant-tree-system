package com.restaurant.tree.app.dto;

/**
 * DTO plano exclusivo para respuestas de recorridos (BFS/DFS).
 * No contiene jerarquías para evitar duplicación visual en el JSON.
 */
public class TraversalNodeResponse {

    private Long id;
    private String value;

    public TraversalNodeResponse() {
    }

    public TraversalNodeResponse(Long id, String value) {
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