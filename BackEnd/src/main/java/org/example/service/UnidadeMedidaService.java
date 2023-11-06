package org.example.service;

import org.example.model.UnidadeMedida;
import org.example.repository.UnidadeMedidaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeMedidaService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UnidadeMedidaRepository repository;

    public UnidadeMedida salvar(UnidadeMedida entity) {
        return repository.save(entity);
    }

    public List<UnidadeMedida> buscaTodos(String filter) {
        return repository.findAll(filter, UnidadeMedida.class);
    }

    public Page<UnidadeMedida> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, UnidadeMedida.class, pageable);
    }

    public UnidadeMedida buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UnidadeMedida alterar(Long id, UnidadeMedida entity) {
        Optional<UnidadeMedida> existingUnidadeMedidaOptional = repository.findById(id);
        if(existingUnidadeMedidaOptional.isEmpty()) {
            throw new NotFoundException("Unidade de medida não encontrada!");
        }
        UnidadeMedida existingUnidadeMedida = existingUnidadeMedidaOptional.get();
        modelMapper.map(entity, existingUnidadeMedida);
        return repository.save(existingUnidadeMedida);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}