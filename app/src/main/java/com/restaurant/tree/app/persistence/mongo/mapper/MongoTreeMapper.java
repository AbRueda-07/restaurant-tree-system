package com.restaurant.tree.app.persistence.mongo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.restaurant.tree.app.persistence.mongo.document.MongoTreeDocument;
import com.restaurant.tree.engine.model.TreeNode;

@Component
public class MongoTreeMapper {

    public MongoTreeDocument toDocument(TreeNode node) {

        if (node == null) {
            return null;
        }

        MongoTreeDocument document =
                new MongoTreeDocument(String.valueOf(node.getId()), node.getValue());

        List<MongoTreeDocument> children = new ArrayList<>();

        for (TreeNode child : node.getChildren()) {
            children.add(toDocument(child));
        }

        document.setChildren(children);

        return document;
    }

    public TreeNode toTreeNode(MongoTreeDocument document) {

        if (document == null) {
            return null;
        }

        TreeNode node =
                new TreeNode(Long.valueOf(document.getId()), document.getValue());

        List<TreeNode> children = new ArrayList<>();

        for (MongoTreeDocument child : document.getChildren()) {

            TreeNode childNode = toTreeNode(child);

            childNode.setParent(node);

            children.add(childNode);
        }

        node.setChildren(children);

        return node;
    }
}