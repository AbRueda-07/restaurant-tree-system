package com.restaurant.tree.app.persistence.memory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.restaurant.tree.app.persistence.TreeRepository;
import com.restaurant.tree.engine.model.TreeNode;

@Repository
public class MemoryTreeRepository implements TreeRepository {

    private final Map<Long, TreeNode> trees = new HashMap<>();

    @Override
    public void save(TreeNode root) {

        trees.put(root.getId(), root);
    }

    @Override
    public TreeNode findById(Long id) {

        return trees.get(id);
    }

    @Override
    public Map<Long, TreeNode> findAll() {

        return trees;
    }
}