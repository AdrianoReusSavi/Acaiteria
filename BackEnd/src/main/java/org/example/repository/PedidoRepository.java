package org.example.repository;

import org.example.enterprise.CustomQuerydslPredicateExecutor;
import org.example.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, CustomQuerydslPredicateExecutor<Pedido> {

}