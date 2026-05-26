package com.restaurant.tree.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.restaurant.tree.app.dto.TraversalNodeResponse;
import com.restaurant.tree.app.dto.TreeNodeRequest;
import com.restaurant.tree.app.dto.TreeNodeResponse;
import com.restaurant.tree.app.mapper.NodeResponseMapper;
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
    public TreeNodeResponse createRoot(@RequestBody TreeNodeRequest request) {
        TreeNode root = treeService.createRoot(request.getId(), request.getValue());
        return NodeResponseMapper.toResponse(root);
    }

    @PostMapping("/{parentId}/child")
    public TreeNodeResponse addChild(
            @PathVariable Long parentId,
            @RequestBody TreeNodeRequest request) {

        TreeNode child = treeService.addChild(
                parentId,
                request.getId(),
                request.getValue()
        );
        return NodeResponseMapper.toResponse(child);
    }

    @GetMapping("/{rootId}/dfs")
    public List<TraversalNodeResponse> dfs(@PathVariable Long rootId) {
        List<TreeNode> nodes = treeService.dfs(rootId);
        return nodes.stream()
                .map(node -> NodeResponseMapper.toTraversalDto(node))
                .collect(Collectors.toList());
    }

    @GetMapping("/{rootId}/bfs")
    public List<TraversalNodeResponse> bfs(@PathVariable Long rootId) {
        List<TreeNode> nodes = treeService.bfs(rootId);
        return nodes.stream()
                .map(node -> NodeResponseMapper.toTraversalDto(node))
                .collect(Collectors.toList());
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
    public TreeNodeResponse findTree(@PathVariable Long rootId) {
        TreeNode root = treeService.findById(rootId);
        return NodeResponseMapper.toResponse(root);
    }
}
