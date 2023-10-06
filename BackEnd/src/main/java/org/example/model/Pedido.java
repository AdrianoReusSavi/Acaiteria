package org.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido extends EntityId {

    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoItem> pedidoItems = new ArrayList<>();

    @Column(name = "data")
    private Date data_hora;
    @Column(name = "valor_total")
    private double valor_total;
    @Column(name = "desconto")
    private double desconto;
    @Column(name = "metodo_id")
    private Integer metodo;
    @Column(name = "condicao_id")
    private Integer condicao;
    @Column(name = "cliente")
    private String cliente;

    //region getters e setters

    public Date getData_hora() {
        return data_hora;
    }

    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    //endregion
}
