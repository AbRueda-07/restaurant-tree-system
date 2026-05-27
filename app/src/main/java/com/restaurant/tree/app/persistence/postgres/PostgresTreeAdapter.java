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
import java.util.ArrayList;

@Component
// Control dinámico de persistencia: se activa solo cuando app.storage sea postgres
@ConditionalOnProperty(name = "app.storage", havingValue = "postgres")
public class PostgresTreeAdapter implements TreeRepository {

    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public void save(TreeNode root) {
        if (root != null) {
            // Convertimos recursivamente todo el árbol de dominio a entidades vinculadas
            NodeEntity rootEntity = convertToEntityRecursively(root, null);
            // Al guardar la raíz, JPA guardará todos los hijos automáticamente por la cascada
            nodeRepository.save(rootEntity);
        }
    }

    @Override
    public TreeNode findById(Long id) {
        // Búsqueda en PostgreSQL y transformación del árbol mediante el mapper
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
            TreeNode node = NodeMapper.toModel(entity); 
            if (node != null) {
                treeMap.put(node.getId(), node);
            }
        }
        return treeMap;
    }

    /**
     * Convierte un TreeNode de dominio a NodeEntity de base de datos de manera recursiva,
     * estableciendo las relaciones padre-hijo bidireccionales necesarias para JPA.
     */
    private NodeEntity convertToEntityRecursively(TreeNode domainNode, NodeEntity parentEntity) {
        if (domainNode == null) {
            return null;
        }

        NodeEntity entity = new NodeEntity();
        entity.setId(domainNode.getId());
        entity.setValue(domainNode.getValue());
        entity.setParent(parentEntity); // Asignamos el padre

        // Procesar los hijos de forma recursiva si existen
        if (domainNode.getChildren() != null) {
            List<NodeEntity> childEntities = new ArrayList<>();
            for (TreeNode domainChild : domainNode.getChildren()) {
                // Llamada recursiva para convertir al hijo y pasarle este nodo como su padre
                NodeEntity childEntity = convertToEntityRecursively(domainChild, entity);
                if (childEntity != null) {
                    childEntities.add(childEntity);
                }
            }
            entity.setChildren(childEntities);
        }

        return entity;
    }

    @Override
    public void deleteById(Long id) {
        if (id != null && nodeRepository.existsById(id)) {
            nodeRepository.deleteById(id);
        }
    }
    
    
}