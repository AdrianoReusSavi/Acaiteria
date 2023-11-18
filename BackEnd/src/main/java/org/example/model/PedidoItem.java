package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class PedidoItem extends EntityId {
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonIgnoreProperties("pedidoItens")
    private Pedido pedido;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @NotNull @NotBlank @Size(max = 250)
    @Column(name = "descricao_item", nullable = false, length = 250)
    private String descricaoItem;
    @NotNull @Positive
    @Column(name = "valor_venda", nullable = false, precision = 10, scale = 2)
    private Double valorVenda;
    @NotNull @Positive
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    //region Getters e Setters
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    //endregion
}