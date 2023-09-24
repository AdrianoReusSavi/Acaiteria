package org.example.model;

public class ItemVendavel {
    private String descricao;
    private Double valorUnitario;
    private Boolean estocavel;
    //region construtores

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }


    //endregion
}
