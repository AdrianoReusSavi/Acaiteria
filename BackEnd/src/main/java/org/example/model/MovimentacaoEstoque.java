package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class MovimentacaoEstoque extends EntityId {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @NotNull @Positive
    @Column(name = "quantidade_movimento", nullable = false)
    private Integer quantidadeMovimento;
    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDateTime dataHora;
    @NotNull @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoMovimentacao tipo;
    @NotNull @Positive
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
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