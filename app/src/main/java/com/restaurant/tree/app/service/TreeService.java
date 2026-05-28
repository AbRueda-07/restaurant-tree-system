package com.restaurant.tree.app.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.restaurant.tree.app.persistence.TreeRepository;
import com.restaurant.tree.engine.model.TreeNode;
import com.restaurant.tree.engine.strategy.TreeAlgorithmStrategy;

@Service
public class TreeService {

    private final TreeAlgorithmStrategy strategy;
    private final TreeRepository repository;

    public TreeService(
            TreeRepository repository,
            TreeAlgorithmStrategy strategy
    ) {
        this.repository = repository;
        this.strategy = strategy;
    }

    public TreeNode createRoot(Long id, String value) {

        TreeNode root = strategy.createRoot(id, value);

        repository.save(root);

        return root;
    }

    public TreeNode addChild(Long parentId, Long childId, String childValue) {

        LocatedNode locatedParent = findPersistedNodeWithOwner(parentId);

        if (locatedParent == null) {
            TreeNode parent = repository.findById(parentId);

            if (parent != null) {
                locatedParent = new LocatedNode(findOwnerRoot(parent), parent);
            }
        }

        if (locatedParent == null) {
            return null;
        }

        TreeNode child = strategy.addChild(locatedParent.node, childId, childValue);

        repository.save(locatedParent.root);

        return child;
    }

    public TreeNode findById(Long id) {

        TreeNode directMatch = repository.findById(id);

        if (directMatch != null) {
            return directMatch;
        }

        LocatedNode locatedNode = findPersistedNodeWithOwner(id);

        return locatedNode == null ? null : locatedNode.node;
    }

    public List<TreeNode> dfs(Long rootId) {

        TreeNode root = repository.findById(rootId);

        return strategy.dfsTraversal(root);
    }

    public List<TreeNode> bfs(Long rootId) {

        TreeNode root = repository.findById(rootId);

        return strategy.bfsTraversal(root);
    }

    public int height(Long rootId) {

        TreeNode root = repository.findById(rootId);

        return strategy.calculateHeight(root);
    }

    public boolean validate(Long rootId) {

        TreeNode root = repository.findById(rootId);

        return strategy.validateNoCycles(root);
    }

    private LocatedNode findPersistedNodeWithOwner(Long nodeId) {

        Map<Long, TreeNode> persistedTrees = repository.findAll();

        if (persistedTrees == null || persistedTrees.isEmpty()) {
            return null;
        }

        LocatedNode bestMatch = null;
        int bestTreeSize = -1;

        for (TreeNode candidateRoot : persistedTrees.values()) {

            LocatedNode match = findNode(candidateRoot, nodeId, candidateRoot);

            if (match == null) {
                continue;
            }

            int treeSize = countNodes(candidateRoot);

            if (treeSize > bestTreeSize) {
                bestMatch = match;
                bestTreeSize = treeSize;
            }
        }

        return bestMatch;
    }
    
   
    public TreeNode updateNode(Long id, String newValue) {
        LocatedNode located = findPersistedNodeWithOwner(id);
        
        
        if (located == null) {
            TreeNode root = repository.findById(id);
            if (root != null) {
                located = new LocatedNode(root, root);
            }
        }

        if (located == null) {
            throw new IllegalArgumentException("No se encontró el nodo con ID: " + id);
        }

        
        located.node.setValue(newValue);

        
        repository.save(located.root);

        return located.node;
    }

    
    public void deleteNode(Long id) {
        LocatedNode located = findPersistedNodeWithOwner(id);

        if (located == null) {
            
            TreeNode root = repository.findById(id);
            if (root != null) {
                repository.deleteById(id);
                return;
            }
            throw new IllegalArgumentException("No se pudo eliminar: No existe el nodo con ID " + id);
        }

        TreeNode nodeToDelete = located.node;
        TreeNode parent = nodeToDelete.getParent();

        if (parent != null) {
            
            parent.getChildren().remove(nodeToDelete);
            nodeToDelete.setParent(null);
            
            
            repository.save(located.root);
        } else {
            
            repository.deleteById(id);
        }
    }

   
    public List<TreeNode> getChildren(Long id) {
        TreeNode node = findById(id);
        if (node == null) {
            throw new IllegalArgumentException("No se encontró el nodo con ID: " + id);
        }
        return node.getChildren();
    }

    
    public boolean exists(Long id) {
        return findById(id) != null;
    }
    private LocatedNode findNode(TreeNode currentNode, Long nodeId, TreeNode ownerRoot) {

        if (currentNode == null) {
            return null;
        }

        if (Objects.equals(currentNode.getId(), nodeId)) {
            return new LocatedNode(ownerRoot, currentNode);
        }

        for (TreeNode child : currentNode.getChildren()) {

            LocatedNode match = findNode(child, nodeId, ownerRoot);

            if (match != null) {
                return match;
            }
        }

        return null;
    }

    private int countNodes(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int count = 1;

        for (TreeNode child : node.getChildren()) {
            count += countNodes(child);
        }

        return count;
    }

    private TreeNode findOwnerRoot(TreeNode node) {

        TreeNode current = node;

        while (current.getParent() != null) {
            current = current.getParent();
        }

        return current;
    }

    private static class LocatedNode {

        private final TreeNode root;
        private final TreeNode node;

        private LocatedNode(TreeNode root, TreeNode node) {
            this.root = root;
            this.node = node;
        }
    }
}
