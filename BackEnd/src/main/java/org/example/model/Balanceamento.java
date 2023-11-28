package org.example.model;

public class Balanceamento {
    private String data;
    private String nomeItem;
    private TipoMovimentacao tipo;
    private Integer qtdCompras;
    private Integer qtdVendas;
    private Double lucroBruto;
    private Double lucro;

    //region Getters e Setters
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public Integer getQtdCompras() {
        return qtdCompras;
    }

    public void setQtdCompras(Integer qtdCompras) {
        this.qtdCompras = qtdCompras;
    }

    public Integer getQtdVendas() {
        return qtdVendas;
    }

    public void setQtdVendas(Integer qtdVendas) {
        this.qtdVendas = qtdVendas;
    }

    public Double getLucroBruto() {
        return lucroBruto;
    }

    public void setLucroBruto(Double lucroBruto) {
        this.lucroBruto = lucroBruto;
    }

    public Double getLucro() {
        return lucro;
    }

    public void setLucro(Double lucro) {
        this.lucro = lucro;
    }
    //endregion
}