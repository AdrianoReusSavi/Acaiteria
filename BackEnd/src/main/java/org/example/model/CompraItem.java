package org.example.model;

import javax.persistence.*;

@Entity
public class CompraItem extends EntityId {

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne
    @JoinColumn(name = "tipo_item_id")
    private TipoItem tipoItem;

    @Column(name = "descricao_item")
    private String descricaoItem;
    @Column(name = "valor_item")
    private double valorItem;
    @Column(name = "quantidade")
    private double quantidade;

    //region getters e setters

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    //endregion
}
