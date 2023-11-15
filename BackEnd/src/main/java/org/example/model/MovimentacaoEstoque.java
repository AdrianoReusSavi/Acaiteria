package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
public class MovimentacaoEstoque extends EntityId {
    @NotNull @NotBlank
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @NotNull @NotBlank @Positive
    @Column(name = "quantidade_movimento", nullable = false)
    private Integer quantidadeMovimento;
    @NotNull @NotBlank
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @NotNull @NotBlank
    @Column(name = "tipo", nullable = false, length = 1)
    private String tipo;
    @NotNull @NotBlank @Positive
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