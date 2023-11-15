package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class UnidadeMedida extends EntityId {
    @NotNull @NotBlank @Max(20)
    @Column(name = "sigla", nullable = false, length = 20)
    private String sigla;
    @NotNull @NotBlank @Max(50)
    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;
    //region Getters e Setters
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    //endregion
}