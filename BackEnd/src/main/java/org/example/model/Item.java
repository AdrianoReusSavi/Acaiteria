package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item extends EntityId {
    @OneToOne
    @JoinColumn(name = "unidade_medida_id")
    @NotNull
    private UnidadeMedida unidadeMedida;

    @Column(name = "descricao", nullable = false, length = 250)
    private String descricao;
    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;
    @Column(name = "preco_compra", nullable = false, precision = 10, scale = 2)
    private Double precoCompra;
    @Column(name = "preco_venda", nullable = false, precision = 10, scale = 2)
    private Double precoVenda;
    @Column(name = "imagem", nullable = false, length = 250)
    private String imagem;
    @Column(name = "filtro", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoItem filtro;
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    //region getters e setters
    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
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