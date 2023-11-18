package org.example.dto;

import org.example.model.Pedido;
import org.example.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDTO {
    private Long id;
    private LocalDateTime dataHora;
    private Double valorTotal;
    private Double desconto;
    private String cliente;
    private Status status;

    //region Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    //region Constructors
    public static PedidoDTO fromEntity(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setDataHora(pedido.getDataHora());
        dto.setValorTotal(pedido.getValorTotal());
        dto.setDesconto(pedido.getDesconto());
        dto.setCliente(pedido.getCliente());
        dto.setStatus(pedido.getStatus());
        return dto;
    }

    public Pedido toEntity() {
        Pedido pedido = new Pedido();
        pedido.setId(this.getId());
        pedido.setDataHora(this.getDataHora());
        pedido.setValorTotal(this.getValorTotal());
        pedido.setDesconto(this.getDesconto());
        pedido.setCliente(this.getCliente());
        pedido.setStatus(this.getStatus());
        return pedido;
    }

    public static List<PedidoDTO> fromEntity(List<Pedido> pedidos) {
        return pedidos.stream().map(pedido -> fromEntity(pedido)).collect(Collectors.toList());
    }

    public static Page<PedidoDTO> fromEntity(Page<Pedido> pedidos) {
        List<PedidoDTO> pedidosFind = pedidos.stream().map(pedido -> fromEntity(pedido)).collect(Collectors.toList());
        Page<PedidoDTO> pedidosDTO = new PageImpl<>(pedidosFind, pedidos.getPageable(), pedidos.getTotalElements());
        return pedidosDTO;
    }
    //endregion
}