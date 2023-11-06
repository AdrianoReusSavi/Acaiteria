package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Status extends EntityId {
    @Column(name = "descricao", nullable = false, length = 20)
    private String descricao;

    //region Getters e Setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    //endregion
}