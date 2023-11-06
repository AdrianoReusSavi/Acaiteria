package org.example.repository;

import org.example.enterprise.CustomQuerydslPredicateExecutor;
import org.example.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, CustomQuerydslPredicateExecutor<Usuario> {

    @Query("SELECT u FROM Usuario u WHERE u.login = :user AND u.senha = :password")
    Usuario findByUserAndPassword(@Param("user") String user, @Param("password") String password);
}