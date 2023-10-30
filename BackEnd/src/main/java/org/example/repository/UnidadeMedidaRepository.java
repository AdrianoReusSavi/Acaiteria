package org.example.repository;

import org.example.model.UnidadeMedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Long>, CustomQuerydslPredicateExecutor<UnidadeMedida> {

}
