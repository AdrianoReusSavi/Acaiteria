package org.example.dto;

import org.example.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDTO {
    private Long id;
    private Date dataHora;
    private Double valorTotal;
    private Double desconto;
    private String cliente;

    //region Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    //region Constructors
    public static PedidoDTO fromEntity(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setDataHora(pedido.getDataHora());
        dto.setValorTotal(pedido.getValorTotal());
        dto.setDesconto(pedido.getDesconto());
        dto.setCliente(pedido.getCliente());
        return dto;
    }

    public Pedido toEntity() {
        Pedido pedido = new Pedido();
        pedido.setId(this.getId());
        pedido.setDataHora(this.getDataHora());
        pedido.setValorTotal(this.getValorTotal());
        pedido.setDesconto(this.getDesconto());
        pedido.setCliente(this.getCliente());
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