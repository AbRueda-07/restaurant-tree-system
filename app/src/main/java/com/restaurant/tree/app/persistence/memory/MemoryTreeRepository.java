package com.restaurant.tree.app.persistence.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

        TreeNode ownerRoot = findOwnerRoot(root);

        trees.put(ownerRoot.getId(), ownerRoot);
    }

    @Override
    public TreeNode findById(Long id) {

        TreeNode directMatch = trees.get(id);

        if (directMatch != null) {
            return directMatch;
        }

        for (TreeNode root : trees.values()) {

            TreeNode match = findInTree(root, id);

            if (match != null) {
                return match;
            }
        }

        return null;
    }

    @Override
    public Map<Long, TreeNode> findAll() {

        return trees;
    }

    private TreeNode findOwnerRoot(TreeNode node) {

        TreeNode current = node;

        while (current.getParent() != null) {
            current = current.getParent();
        }

        return current;
    }

    private TreeNode findInTree(TreeNode currentNode, Long id) {

        if (currentNode == null) {
            return null;
        }

        if (Objects.equals(currentNode.getId(), id)) {
            return currentNode;
        }

        for (TreeNode child : currentNode.getChildren()) {

            TreeNode match = findInTree(child, id);

            if (match != null) {
                return match;
            }
        }

        return null;
    }
}
