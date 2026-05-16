package com.restaurant.tree.app.persistence.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un nodo del árbol en PostgreSQL.
 * Implementa una estructura jerárquica mediante una relación autorreferenciada.
 */
@Entity
@Table(name = "nodes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String value; // Mapeo directo al campo 'value' del modelo del motor (TreeNode)

    /**
     * Relación Muchos-a-Uno (Autorreferenciada).
     * Muchos nodos pueden tener el mismo padre.
     * Se usa FetchType.LAZY para optimizar el rendimiento al no cargar el padre innecesariamente.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private NodeEntity parent;

    /**
     * Relación Uno-a-Muchos.
     * Un nodo puede tener múltiples hijos.
     * 'cascade = CascadeType.ALL' asegura que si se borra un nodo, se borren sus hijos (integridad).
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NodeEntity> children = new ArrayList<>();
}