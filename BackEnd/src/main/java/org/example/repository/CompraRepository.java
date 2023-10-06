package org.example.repository;

import org.example.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>, CustomQuerydslPredicateExecutor<Compra> {

}