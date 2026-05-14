package com.restaurant.tree.engine.strategy;

import java.util.List;

import com.restaurant.tree.engine.model.TreeNode;

public interface TreeAlgorithmStrategy {

    TreeNode createRoot(Long id, String value);

    TreeNode addChild(TreeNode parent, Long childId, String childValue);

    List<TreeNode> dfsTraversal(TreeNode root);

    List<TreeNode> bfsTraversal(TreeNode root);

    int calculateHeight(TreeNode root);

    int calculateDepth(TreeNode node);

    List<TreeNode> getAncestors(TreeNode node);

    boolean validateNoCycles(TreeNode root);
}