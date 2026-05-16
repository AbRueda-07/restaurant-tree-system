package com.restaurant.tree.app.repository;

import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.restaurant.tree.engine.model.TreeNode;

@Repository
@ConditionalOnProperty(
        name = "app.storage",
        havingValue = "memory",
        matchIfMissing = true
)
public class MemoryTreeStorageAdapter implements TreeStorageRepository {

    private final MemoryTreeRepository memoryTreeRepository;

    public MemoryTreeStorageAdapter(MemoryTreeRepository memoryTreeRepository) {
        this.memoryTreeRepository = memoryTreeRepository;
    }

    @Override
    public void save(TreeNode root) {
        memoryTreeRepository.save(root);
    }

    @Override
    public TreeNode findById(Long id) {
        return memoryTreeRepository.findById(id);
    }

    @Override
    public Map<Long, TreeNode> findAll() {
        return memoryTreeRepository.findAll();
    }
}