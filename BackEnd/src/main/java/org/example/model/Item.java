package org.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item extends EntityId {

    @OneToOne
    @JoinColumn(name = "unidade_medida_id")
    private UnidadeMedida unidadeMedida;

    @OneToMany(mappedBy = "item")
    private List<MovimentacaoEstoque> movimentacaoEstoques = new ArrayList<>();

    @Column(name = "descricao")
    private String descricao;
    @Column(name = "quantidade_estoque")
    private double quantidade_estoque;
    @Column(name = "preco_compra")
    private double preco_compra;
    @Column(name = "preco_venda")
    private double preco_venda;

    //region getters e setters

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public void setQuantidade_estoque(double quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public double getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(double preco_compra) {
        this.preco_compra = preco_compra;
    }

    public double getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(double preco_venda) {
        this.preco_venda = preco_venda;
    }
    //endregion
}
