package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido extends EntityId {
    //@OneToOne
    //@JoinColumn(name = "status_id")
    //@NotNull(message = "O status do pedido deve ser informado!")
    //private Status status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> pedidoItens = new ArrayList<>();

    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private Double valorTotal;
    @Column(name = "desconto", nullable = false, precision = 10, scale = 2)
    private Double desconto;
    @Column(name = "cliente", nullable = false, length = 60)
    private String cliente;

    //region getters e setters

    //public Status getStatus() {
    //    return status;
    //}

    //public void setStatus(Status status) {
    //    this.status = status;
    //}

    public List<PedidoItem> getPedidoItens() {
        return pedidoItens;
    }

    public void setPedidoItens(List<PedidoItem> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
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