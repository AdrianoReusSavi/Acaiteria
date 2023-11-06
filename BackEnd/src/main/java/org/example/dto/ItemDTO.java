package org.example.dto;

import org.example.model.Item;
import org.example.model.MovimentacaoEstoque;
import org.example.model.TipoItem;
import org.example.model.UnidadeMedida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemDTO {
    private Long id;
    private UnidadeMedida unidadeMedida;
    private String descricao;
    private Integer quantidadeEstoque;
    private Double precoCompra;
    private Double precoVenda;
    private String imagem;
    private TipoItem filtro;
    private Boolean ativo;

    //region Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public TipoItem getFiltro() {
        return filtro;
    }

    public void setFiltro(TipoItem filtro) {
        this.filtro = filtro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    //endregion

    //region Constructors
    public static ItemDTO fromEntity(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setUnidadeMedida(item.getUnidadeMedida());
        dto.setDescricao(item.getDescricao());
        dto.setQuantidadeEstoque(item.getQuantidadeEstoque());
        dto.setPrecoCompra(item.getPrecoCompra());
        dto.setPrecoVenda(item.getPrecoVenda());
        dto.setImagem(item.getImagem());
        dto.setFiltro(item.getFiltro());
        dto.setAtivo(item.getAtivo());
        return dto;
    }

    public Item toEntity() {
        Item item = new Item();
        item.setId(this.getId());
        item.setUnidadeMedida(this.getUnidadeMedida());
        item.setDescricao(this.getDescricao());
        item.setQuantidadeEstoque(this.getQuantidadeEstoque());
        item.setPrecoCompra(this.getPrecoCompra());
        item.setPrecoVenda(this.getPrecoVenda());
        item.setImagem(this.getImagem());
        item.setFiltro(this.getFiltro());
        item.setAtivo(this.getAtivo());
        return item;
    }

    public static List<ItemDTO> fromEntity(List<Item> items) {
        return items.stream().map(item -> fromEntity(item)).collect(Collectors.toList());
    }

    public static Page<ItemDTO> fromEntity(Page<Item> items) {
        List<ItemDTO> itemsFind = items.stream().map(item -> fromEntity(item)).collect(Collectors.toList());
        Page<ItemDTO> itemsDTO = new PageImpl<>(itemsFind, items.getPageable(), items.getTotalElements());
        return itemsDTO;
    }
    //endregion
}