package com.restaurant.tree.engine.model;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private Long id;
    private String value;
    private TreeNode parent;
    private List<TreeNode> children;

    public TreeNode() {
        this.children = new ArrayList<>();
    }

    public TreeNode(Long id, String value) {
        this.id = id;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public void addChild(TreeNode child) {
        child.setParent(this);
        this.children.add(child);
    }
}