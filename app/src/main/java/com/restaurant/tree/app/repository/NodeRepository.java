package com.restaurant.tree.app.repository;


import com.restaurant.tree.app.persistence.entity.NodeEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de persistencia de nodos en PostgreSQL.
 * Implementa JpaRepository para obtener operaciones CRUD automáticas.
 */
@Repository
/* 
 * El bean solo se cargará si en application.properties se define app.storage=postgres.
 */
@ConditionalOnProperty(name = "app.storage", havingValue = "postgres")
public interface NodeRepository extends JpaRepository<NodeEntity, Long> {
    // Hereda métodos como findById, findAll, save y delete para NodeEntity.
}