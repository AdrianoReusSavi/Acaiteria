package org.example.dto;

import org.example.model.PedidoItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoItemDTO {
    private Long id;
    private String descricaoItem;
    private Double valorVenda;
    private Integer quantidade;

    //region Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    //endregion

    //region Constructors
    public static PedidoItemDTO fromEntity(PedidoItem pedidoItem) {
        PedidoItemDTO dto = new PedidoItemDTO();
        dto.setId(pedidoItem.getId());
        dto.setDescricaoItem(pedidoItem.getDescricaoItem());
        dto.setValorVenda(pedidoItem.getValorVenda());
        dto.setQuantidade(pedidoItem.getQuantidade());
        return dto;
    }

    public PedidoItem toEntity() {
        PedidoItem pedidoItem = new PedidoItem();
        pedidoItem.setId(this.getId());
        pedidoItem.setDescricaoItem(this.getDescricaoItem());
        pedidoItem.setValorVenda(this.getValorVenda());
        pedidoItem.setQuantidade(this.getQuantidade());
        return pedidoItem;
    }

    public static List<PedidoItemDTO> fromEntity(List<PedidoItem> pedidoItems) {
        return pedidoItems.stream().map(pedidoItem -> fromEntity(pedidoItem)).collect(Collectors.toList());
    }

    public static Page<PedidoItemDTO> fromEntity(Page<PedidoItem> pedidoItems) {
        List<PedidoItemDTO> pedidoItemsFind = pedidoItems.stream().map(pedidoItem -> fromEntity(pedidoItem)).collect(Collectors.toList());
        Page<PedidoItemDTO> pedidoItemsDTO = new PageImpl<>(pedidoItemsFind, pedidoItems.getPageable(), pedidoItems.getTotalElements());
        return pedidoItemsDTO;
    }
    //endregion
}