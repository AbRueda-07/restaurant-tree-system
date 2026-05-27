package com.restaurant.tree.app.mapper;

import com.restaurant.tree.app.dto.TraversalNodeResponse;
import com.restaurant.tree.app.dto.TreeNodeResponse;
import com.restaurant.tree.engine.model.TreeNode;

/**
 * Mapper responsable de transformar el modelo de dominio en el DTO de presentación.
 */
public class NodeResponseMapper {
    
    /**
     * Transforma un TreeNode del motor a un TraversalNodeResponse plano (sin hijos).
     */
    public static TraversalNodeResponse toTraversalDto(TreeNode node) {
        if (node == null) {
            return null;
        }
        return new TraversalNodeResponse(node.getId(), node.getValue());
    }

    /**
     * Transforma un TreeNode del motor a un TreeNodeResponse jerárquico y limpio.
     */
    public static TreeNodeResponse toResponse(TreeNode node) {
        if (node == null) return null;

        // Cree el DTO plano (sin padre)
        TreeNodeResponse response = new TreeNodeResponse(node.getId(), node.getValue());

        // Mapea recursivamente a los hijos
        if (node.getChildren() != null) {
            for (TreeNode child : node.getChildren()) {
                response.addChild(toResponse(child));
            }
        }

        return response;
    }
}

