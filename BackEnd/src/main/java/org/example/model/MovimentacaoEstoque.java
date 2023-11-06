package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class MovimentacaoEstoque extends EntityId {
    @ManyToOne
    @JoinColumn(name = "item_id")
    @NotNull
    private Item item;

    @Column(name = "quantidade_movimento", nullable = false)
    private Integer quantidadeMovimento;
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @Column(name = "tipo", nullable = false, length = 1)
    private String tipo;
    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private Double valor;

    //region getters e setters
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantidadeMovimento() {
        return quantidadeMovimento;
    }

    public void setQuantidadeMovimento(Integer quantidadeMovimento) {
        this.quantidadeMovimento = quantidadeMovimento;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    //endregion
}