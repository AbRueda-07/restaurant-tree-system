package com.restaurant.tree.app.repository;

import java.util.Map;

import com.restaurant.tree.engine.model.TreeNode;

public interface TreeStorageRepository {

    void save(TreeNode root);

    TreeNode findById(Long id);

    Map<Long, TreeNode> findAll();
}