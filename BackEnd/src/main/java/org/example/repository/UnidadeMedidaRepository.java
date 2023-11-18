package org.example.repository;

import org.example.enterprise.CustomQuerydslPredicateExecutor;
import org.example.model.UnidadeMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Long>, CustomQuerydslPredicateExecutor<UnidadeMedida> {

}
