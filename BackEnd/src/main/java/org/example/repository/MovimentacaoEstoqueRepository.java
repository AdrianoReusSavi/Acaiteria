package org.example.repository;

import org.example.enterprise.CustomQuerydslPredicateExecutor;
import org.example.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long>, CustomQuerydslPredicateExecutor<MovimentacaoEstoque> {
    @Query(nativeQuery = true, value = "EXEC balanceamento @Tipo = :tipo, @DataInicial =:dataInicial, @DataFinal =:dataFinal")
    List<Object[]> balanceamento(@Param("tipo") String tipo, @Param("dataInicial") String dataInicial, @Param("dataFinal") String dataFinal);
    @Query(nativeQuery = true, value = "EXEC fechamento @DataRelatorio = :dataRelatorio")
    List<Object[]> fechamento(@Param("dataRelatorio") String dataRelatorio);
}