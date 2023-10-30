package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class MovimentacaoEstoque extends EntityId {

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "quantidade_movimento")
    private double quantidade_movimento;
    @Column(name = "data")
    private Date data_hora;
    @Column(name = "tipo")
    private char tipo;
    @Column(name = "valor")
    private double valor;

    //region getters e setters

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getQuantidade_movimento() {
        return quantidade_movimento;
    }

    public void setQuantidade_movimento(double quantidade_movimento) {
        this.quantidade_movimento = quantidade_movimento;
    }

    public Date getData_hora() {
        return data_hora;
    }

    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    //endregion
}
