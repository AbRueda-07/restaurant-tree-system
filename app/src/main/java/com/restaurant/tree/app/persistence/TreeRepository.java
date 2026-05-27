package com.restaurant.tree.app.persistence;

import java.util.Map;

import com.restaurant.tree.engine.model.TreeNode;

public interface TreeRepository {

    void save(TreeNode root);

    TreeNode findById(Long id);

    Map<Long, TreeNode> findAll();
    
    void deleteById(Long id);
}