package org.example.repository;

import org.example.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>, CustomQuerydslPredicateExecutor<Fornecedor> {

}