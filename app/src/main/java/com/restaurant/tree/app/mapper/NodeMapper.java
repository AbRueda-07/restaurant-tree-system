package com.restaurant.tree.app.mapper;

import com.restaurant.tree.app.persistence.entity.NodeEntity;
import com.restaurant.tree.engine.model.TreeNode;

/**
 * Clase utilitaria para transformar entidades de persistencia en modelos de dominio.
 * Actúa como puente entre la capa de datos (PostgreSQL) y el motor de algoritmos.
 */
public class NodeMapper {

    /**
     * Convierte de forma recursiva una NodeEntity a un TreeNode.
     * @param entity La entidad cargada desde la base de datos.
     * @return El modelo TreeNode listo para ser procesado por las estrategias del motor.
     */
    public static TreeNode toModel(NodeEntity entity) {
        if (entity == null) return null;

        // Se crea el nodo base con los datos planos de la entidad
        TreeNode node = new TreeNode(entity.getId(), entity.getValue());

        // Transformación recursiva de la jerarquía:
        // Por cada hijo en la base de datos, se llama a este mismo método
        // para construir el árbol completo en memoria.
        if (entity.getChildren() != null) {
            entity.getChildren().forEach(child -> node.addChild(toModel(child)));
        }

        return node;
    }
}