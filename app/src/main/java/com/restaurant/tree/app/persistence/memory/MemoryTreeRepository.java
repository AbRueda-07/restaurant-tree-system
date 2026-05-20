package com.restaurant.tree.app.persistence.memory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import com.restaurant.tree.app.persistence.TreeRepository;
import com.restaurant.tree.engine.model.TreeNode;

@Repository
@ConditionalOnProperty(
	    name = "app.storage",
	    havingValue = "memory"
	)
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