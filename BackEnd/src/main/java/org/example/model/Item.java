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
    private Double quantidade_estoque;
    @Column(name = "preco_compra")
    private Double preco_compra;
    @Column(name = "preco_venda")
    private Double preco_venda;
    @Column(name = "imagem")
    private String imagem;
    @Column(name = "filtro")
    private TipoItem filtro;
    @Column(name = "ativo")
    private Boolean ativo;

    //region getters e setters
    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public List<MovimentacaoEstoque> getMovimentacaoEstoques() {
        return movimentacaoEstoques;
    }

    public void setMovimentacaoEstoques(List<MovimentacaoEstoque> movimentacaoEstoques) {
        this.movimentacaoEstoques = movimentacaoEstoques;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public void setQuantidade_estoque(Double quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public Double getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(Double preco_compra) {
        this.preco_compra = preco_compra;
    }

    public Double getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(Double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public TipoItem getFiltro() {
        return filtro;
    }

    public void setFiltro(TipoItem filtro) {
        this.filtro = filtro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    //endregion
}
