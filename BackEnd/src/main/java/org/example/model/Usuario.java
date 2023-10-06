package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class Usuario extends EntityId {
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "permissao")
    private Integer permissao;
}
