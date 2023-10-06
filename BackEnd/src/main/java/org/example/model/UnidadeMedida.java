package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UnidadeMedida extends EntityId {
    @Column(name = "sigla")
    private String sigla;
    @Column(name = "descricao")
    private String descricao;

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
}
