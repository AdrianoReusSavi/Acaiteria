package org.example.model;

import javax.persistence.*;

@Entity
public class PedidoItem extends EntityId {

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "descricao_item")
    private String descricao_item;
    @Column(name = "valor_venda")
    private double valor_venda;

    public String getDescricao_item() {
        return descricao_item;
    }

    public void setDescricao_item(String descricao_item) {
        this.descricao_item = descricao_item;
    }

    public double getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(double valor_venda) {
        this.valor_venda = valor_venda;
    }
}
