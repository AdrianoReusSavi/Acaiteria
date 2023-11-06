package org.example.repository;

import org.example.enterprise.CustomQuerydslPredicateExecutor;
import org.example.model.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long>, CustomQuerydslPredicateExecutor<PedidoItem> {

}