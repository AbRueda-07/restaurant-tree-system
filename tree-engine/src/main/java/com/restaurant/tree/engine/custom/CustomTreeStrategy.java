package com.restaurant.tree.engine.custom;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.restaurant.tree.engine.model.TreeNode;
import com.restaurant.tree.engine.strategy.TreeAlgorithmStrategy;

public class CustomTreeStrategy implements TreeAlgorithmStrategy {

    @Override
    public TreeNode createRoot(Long id, String value) {
        return new TreeNode(id, value);
    }

    @Override
    public TreeNode addChild(TreeNode parent, Long childId, String childValue) {

        TreeNode child = new TreeNode(childId, childValue);

        parent.addChild(child);

        return child;
    }

    @Override
    public List<TreeNode> dfsTraversal(TreeNode root) {

        List<TreeNode> result = new ArrayList<>();

        dfsRecursive(root, result);

        return result;
    }

    private void dfsRecursive(TreeNode node, List<TreeNode> result) {

        if (node == null) {
            return;
        }

        result.add(node);

        for (TreeNode child : node.getChildren()) {
            dfsRecursive(child, result);
        }
    }

    @Override
    public List<TreeNode> bfsTraversal(TreeNode root) {

        List<TreeNode> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();

        queue.add(root);

        while (!queue.isEmpty()) {

            TreeNode current = queue.poll();

            result.add(current);

            queue.addAll(current.getChildren());
        }

        return result;
    }

    @Override
    public int calculateHeight(TreeNode root) {

        if (root == null) {
            return -1;
        }

        int maxHeight = -1;

        for (TreeNode child : root.getChildren()) {

            int childHeight = calculateHeight(child);

            maxHeight = Math.max(maxHeight, childHeight);
        }

        return maxHeight + 1;
    }

    @Override
    public int calculateDepth(TreeNode node) {

        int depth = 0;

        while (node.getParent() != null) {

            depth++;

            node = node.getParent();
        }

        return depth;
    }

    @Override
    public List<TreeNode> getAncestors(TreeNode node) {

        List<TreeNode> ancestors = new ArrayList<>();

        TreeNode current = node.getParent();

        while (current != null) {

            ancestors.add(current);

            current = current.getParent();
        }

        return ancestors;
    }

    @Override
    public boolean validateNoCycles(TreeNode root) {

        Set<Long> visited = new HashSet<>();

        return validateRecursive(root, visited);
    }

    private boolean validateRecursive(TreeNode node, Set<Long> visited) {

        if (node == null) {
            return true;
        }

        if (visited.contains(node.getId())) {
            return false;
        }

        visited.add(node.getId());

        for (TreeNode child : node.getChildren()) {

            if (!validateRecursive(child, visited)) {
                return false;
            }
        }

        visited.remove(node.getId());

        return true;
    }
}