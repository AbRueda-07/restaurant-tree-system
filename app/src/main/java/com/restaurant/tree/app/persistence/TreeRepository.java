package com.restaurant.tree.app.persistence;

import com.restaurant.tree.engine.model.TreeNode;

public interface TreeRepository {

    TreeNode findTree();

    void saveTree(TreeNode root);

}