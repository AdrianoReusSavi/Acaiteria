package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido extends EntityId {
    //@OneToOne
    //@JoinColumn(name = "status_id")
    //@NotNull(message = "O status do pedido deve ser informado!")
    //private Status status;
    @NotNull @NotBlank
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> pedidoItens = new ArrayList<>();

    @NotNull @NotBlank
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;

    @NotNull @NotBlank @Positive
    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private Double valorTotal;

    @NotNull @NotBlank @Positive
    @Column(name = "desconto", nullable = false, precision = 10, scale = 2)
    private Double desconto;

    @NotNull @NotBlank
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