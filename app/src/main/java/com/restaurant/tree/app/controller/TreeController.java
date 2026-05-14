package com.restaurant.tree.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.restaurant.tree.app.dto.TreeNodeRequest;
import com.restaurant.tree.app.service.TreeService;
import com.restaurant.tree.engine.model.TreeNode;

@RestController
@RequestMapping("/api/tree")
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService) {

        this.treeService = treeService;
    }

    @PostMapping("/root")
    public TreeNode createRoot(@RequestBody TreeNodeRequest request) {

        return treeService.createRoot(request.getId(), request.getValue());
    }

    @PostMapping("/{parentId}/child")
    public TreeNode addChild(
            @PathVariable Long parentId,
            @RequestBody TreeNodeRequest request) {

        return treeService.addChild(
                parentId,
                request.getId(),
                request.getValue()
        );
    }

    @GetMapping("/{rootId}/dfs")
    public List<TreeNode> dfs(@PathVariable Long rootId) {

        return treeService.dfs(rootId);
    }

    @GetMapping("/{rootId}/bfs")
    public List<TreeNode> bfs(@PathVariable Long rootId) {

        return treeService.bfs(rootId);
    }

    @GetMapping("/{rootId}/height")
    public int height(@PathVariable Long rootId) {

        return treeService.height(rootId);
    }

    @GetMapping("/{rootId}/validate")
    public boolean validate(@PathVariable Long rootId) {

        return treeService.validate(rootId);
    }
    
    @GetMapping("/{rootId}")
    public TreeNode findTree(@PathVariable Long rootId) {

        return treeService.findTree(rootId);
    }
}