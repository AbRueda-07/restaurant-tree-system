package com.restaurant.tree.app.persistence.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un nodo del árbol en PostgreSQL.
 * Implementa una estructura jerárquica mediante una relación autorreferenciada.
 */
@Entity
@Table(name = "nodes")
public class NodeEntity {

    @Id
    private Long id; 

    @Column(nullable = false)
    private String value; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private NodeEntity parent;

    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NodeEntity> children = new ArrayList<>();

    
    public NodeEntity() {
    }

    public NodeEntity(Long id, String value, NodeEntity parent, List<NodeEntity> children) {
        this.id = id;
        this.value = value;
        this.parent = parent;
        if (children != null) {
            this.children = children;
        }
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

    public NodeEntity getParent() {
        return parent;
    }

    public void setParent(NodeEntity parent) {
        this.parent = parent;
    }

    public List<NodeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<NodeEntity> children) {
        this.children = children;
    }
}