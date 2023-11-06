package org.example.dto;

import org.example.model.UnidadeMedida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class UnidadeMedidaDTO {
    private Long id;
    private String sigla;
    private String descricao;

    //region Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    //endregion

    //region Constructors
    public static UnidadeMedidaDTO fromEntity(UnidadeMedida unidadeMedida) {
        UnidadeMedidaDTO dto = new UnidadeMedidaDTO();
        dto.setId(unidadeMedida.getId());
        dto.setSigla(unidadeMedida.getSigla());
        dto.setDescricao(unidadeMedida.getDescricao());
        return dto;
    }

    public UnidadeMedida toEntity() {
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida.setId(this.getId());
        unidadeMedida.setSigla(this.getSigla());
        unidadeMedida.setDescricao(this.getDescricao());
        return unidadeMedida;
    }

    public static List<UnidadeMedidaDTO> fromEntity(List<UnidadeMedida> unidadeMedidas) {
        return unidadeMedidas.stream().map(unidadeMedida -> fromEntity(unidadeMedida)).collect(Collectors.toList());
    }

    public static Page<UnidadeMedidaDTO> fromEntity(Page<UnidadeMedida> unidadeMedidas) {
        List<UnidadeMedidaDTO> unidadeMedidasFind = unidadeMedidas.stream().map(unidadeMedida -> fromEntity(unidadeMedida)).collect(Collectors.toList());
        Page<UnidadeMedidaDTO> unidadeMedidasDTO = new PageImpl<>(unidadeMedidasFind, unidadeMedidas.getPageable(), unidadeMedidas.getTotalElements());
        return unidadeMedidasDTO;
    }
    //endregion
}