package org.example.dto;

import org.example.model.MovimentacaoEstoque;
import org.example.model.TipoMovimentacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class MovimentacaoEstoqueDTO {
    private Long id;
    private Integer quantidadeMovimento;
    private TipoMovimentacao tipo;
    private Double valor;

    //region Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidadeMovimento() {
        return quantidadeMovimento;
    }

    public void setQuantidadeMovimento(Integer quantidadeMovimento) {
        this.quantidadeMovimento = quantidadeMovimento;
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

    //region Constructors
    public static MovimentacaoEstoqueDTO fromEntity(MovimentacaoEstoque movimentacaoEstoque) {
        MovimentacaoEstoqueDTO dto = new MovimentacaoEstoqueDTO();
        dto.setId(movimentacaoEstoque.getId());
        dto.setQuantidadeMovimento(movimentacaoEstoque.getQuantidadeMovimento());
        dto.setTipo(movimentacaoEstoque.getTipo());
        dto.setValor(movimentacaoEstoque.getValor());
        return dto;
    }

    public MovimentacaoEstoque toEntity() {
        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
        movimentacaoEstoque.setId(this.getId());
        movimentacaoEstoque.setQuantidadeMovimento(this.getQuantidadeMovimento());
        movimentacaoEstoque.setTipo(this.getTipo());
        movimentacaoEstoque.setValor(this.getValor());
        return movimentacaoEstoque;
    }

    public static List<MovimentacaoEstoqueDTO> fromEntity(List<MovimentacaoEstoque> movimentacaoEstoques) {
        return movimentacaoEstoques.stream().map(movimentacaoEstoque -> fromEntity(movimentacaoEstoque)).collect(Collectors.toList());
    }

    public static Page<MovimentacaoEstoqueDTO> fromEntity(Page<MovimentacaoEstoque> movimentacaoEstoques) {
        List<MovimentacaoEstoqueDTO> movimentacaoEstoquesFind = movimentacaoEstoques.stream().map(movimentacaoEstoque -> fromEntity(movimentacaoEstoque)).collect(Collectors.toList());
        Page<MovimentacaoEstoqueDTO> movimentacaoEstoquesDTO = new PageImpl<>(movimentacaoEstoquesFind, movimentacaoEstoques.getPageable(), movimentacaoEstoques.getTotalElements());
        return movimentacaoEstoquesDTO;
    }
    //endregion
}