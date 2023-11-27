package org.example.repository;

import org.example.enterprise.CustomQuerydslPredicateExecutor;
import org.example.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long>, CustomQuerydslPredicateExecutor<MovimentacaoEstoque> {
    @Procedure(name = "balanceamento")
    List<Object[]> balanceamento(@Param("filter") String filter);
}