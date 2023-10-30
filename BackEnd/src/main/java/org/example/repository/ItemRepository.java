package org.example.repository;

import org.example.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, CustomQuerydslPredicateExecutor<Item> {
    @Query("SELECT i FROM Item i WHERE i.ativo = true")
    List<Item> findAllAtivos();
}