package com.restaurant.tree.app.persistence.postgres;

import com.restaurant.tree.app.persistence.TreeRepository;
import com.restaurant.tree.engine.model.TreeNode;
import com.restaurant.tree.app.mapper.NodeMapper;
import com.restaurant.tree.app.persistence.entity.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Component
// Control dinámico de persistencia: se activa solo cuando app.storage sea postgres
@ConditionalOnProperty(name = "app.storage", havingValue = "postgres")
public class PostgresTreeAdapter implements TreeRepository {

    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public void save(TreeNode root) {
        // Mapeo básico del nodo raíz para persistencia en PostgreSQL
        if (root != null) {
            NodeEntity entity = new NodeEntity();
            entity.setId(root.getId());
            entity.setValue(root.getValue());
            nodeRepository.save(entity);
        }
    }

    @Override
    public TreeNode findById(Long id) {
        // Búsqueda en PostgreSQL y transformación recursiva del árbol mediante el mapper
        return nodeRepository.findById(id)
                .map(NodeMapper::toModel)
                .orElse(null);
    }

    @Override
    public Map<Long, TreeNode> findAll() {
        // Recupera todos los nodos de la BD y los estructura en el mapa requerido por el motor
        List<NodeEntity> entities = nodeRepository.findAll();
        Map<Long, TreeNode> treeMap = new HashMap<>();

        for (NodeEntity entity : entities) {
        	TreeNode node = NodeMapper.toModel(entity); // Ajuste de sintaxis clásico para compatibilidad
            if (node != null) {
                treeMap.put(node.getId(), node);
            }
        }
        return treeMap;
    }
}