package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Status extends EntityId {
    @NotNull @NotBlank @Max(20)
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