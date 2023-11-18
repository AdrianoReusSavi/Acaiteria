package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido extends EntityId {
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> pedidoItens = new ArrayList<>();
    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDateTime dataHora;
    @NotNull @Positive
    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private Double valorTotal;
    @NotNull @PositiveOrZero
    @Column(name = "desconto", nullable = false, precision = 10, scale = 2)
    private Double desconto;
    @NotNull @NotBlank @Size(max = 60)
    @Column(name = "cliente", nullable = false, length = 60)
    private String cliente;
    @NotNull @Enumerated(EnumType.STRING)
    @Column(name = "filtro", nullable = false)
    private Status status;

    //region getters e setters
    public List<PedidoItem> getPedidoItens() {
        return pedidoItens;
    }

    public void setPedidoItens(List<PedidoItem> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    //endregion
}