package com.restaurant.tree.app.persistence.mongo.repository;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.restaurant.tree.app.persistence.TreeRepository;
import com.restaurant.tree.app.persistence.mongo.mapper.MongoTreeMapper;
import com.restaurant.tree.engine.model.TreeNode;

@Repository
@ConditionalOnProperty(
        name = "app.storage",
        havingValue = "mongo"
)
public class MongoTreeStorageAdapter implements TreeRepository {

    private final MongoTreeRepository mongoTreeRepository;
    private final MongoTreeMapper mongoTreeMapper;

    public MongoTreeStorageAdapter(
            MongoTreeRepository mongoTreeRepository,
            MongoTreeMapper mongoTreeMapper) {

        this.mongoTreeRepository = mongoTreeRepository;
        this.mongoTreeMapper = mongoTreeMapper;
    }

    @Override
    public void save(TreeNode root) {
        mongoTreeRepository.save(mongoTreeMapper.toDocument(root));
    }

    @Override
    public TreeNode findById(Long id) {
        return mongoTreeRepository.findById(String.valueOf(id))
                .map(mongoTreeMapper::toTreeNode)
                .orElse(null);
    }

    @Override
    public Map<Long, TreeNode> findAll() {
        return mongoTreeRepository.findAll()
                .stream()
                .map(mongoTreeMapper::toTreeNode)
                .collect(Collectors.toMap(TreeNode::getId, tree -> tree));
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            String stringId = String.valueOf(id);
            if (mongoTreeRepository.existsById(stringId)) {
                mongoTreeRepository.deleteById(stringId);
            }
        }
    }
}
