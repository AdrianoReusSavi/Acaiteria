package org.example.model;

import java.util.Date;

public class MovimentacaoEstoque extends EntityId {
    private double quantidade_movimento;
    private Date data_hora;
    private char tipo;
    private double valor;

    //region getters e setters

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
