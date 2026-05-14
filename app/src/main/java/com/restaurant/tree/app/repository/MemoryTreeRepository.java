package com.restaurant.tree.app.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.restaurant.tree.engine.model.TreeNode;

@Repository
public class MemoryTreeRepository {

    private final Map<Long, TreeNode> trees = new HashMap<>();

    public void save(TreeNode root) {

        trees.put(root.getId(), root);
    }

    public TreeNode findById(Long id) {

        return trees.get(id);
    }

    public Map<Long, TreeNode> findAll() {

        return trees;
    }
}