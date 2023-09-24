package org.example.model;

import java.time.LocalDate;

public class ItemVenda{
    //region atributos
    private ItemVendavel produtoServico;
    private Double valorUnitario;
    private Double quantidade;
    private Double desconto;

    //endregion

    //region construtor

    public ItemVenda(ItemVendavel produtoServico, Double valorUnitario, Double quantidade, Double desconto) {
        this.produtoServico = produtoServico;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.desconto = desconto;
    }

    public ItemVendavel getProdutoServico() {
        return produtoServico;
    }

    public void setProdutoServico(ItemVendavel produtoServico) {
        this.produtoServico = produtoServico;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    //endregion

    public Double getValorCalculado(){
        double valorTotal = this.getValorUnitario() * this.getQuantidade();
        double descontoCalculado = valorTotal * (this.getDesconto() / 100);
        return valorTotal - descontoCalculado;
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                "produtoServico=" + produtoServico +
                ", valorUnitario=" + valorUnitario +
                ", quantidade=" + quantidade +
                ", desconto=" + desconto +
                '}';
    }
}



