package com.restaurant.tree.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurant.tree.app.persistence.TreeRepository;
import com.restaurant.tree.engine.custom.CustomTreeStrategy;
import com.restaurant.tree.engine.model.TreeNode;
import com.restaurant.tree.engine.strategy.TreeAlgorithmStrategy;

@Service
public class TreeService {

    private final TreeAlgorithmStrategy strategy;
    private final TreeRepository repository;

    public TreeService(TreeRepository repository) {

        this.strategy = new CustomTreeStrategy();
        this.repository = repository;
    }

    public TreeNode createRoot(Long id, String value) {

        TreeNode root = strategy.createRoot(id, value);

        repository.save(root);

        return root;
    }

    public TreeNode addChild(Long parentId, Long childId, String childValue) {

        TreeNode parent = repository.findById(parentId);

        if (parent == null) {
            return null;
        }

        TreeNode child = strategy.addChild(parent, childId, childValue);

        repository.save(parent);

        return child;
    }

    public TreeNode findById(Long id) {

        return repository.findById(id);
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
}