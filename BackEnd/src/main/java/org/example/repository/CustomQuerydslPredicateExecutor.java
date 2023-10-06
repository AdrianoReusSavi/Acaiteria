package org.example.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CustomQuerydslPredicateExecutor<T> extends QuerydslPredicateExecutor<T> {
    @Override
    List<T> findAll(Predicate predicate);
}
