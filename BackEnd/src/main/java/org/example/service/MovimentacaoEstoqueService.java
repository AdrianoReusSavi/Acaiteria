package org.example.service;

import org.example.model.MovimentacaoEstoque;
import org.example.repository.MovimentacaoEstoqueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoEstoqueService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovimentacaoEstoqueRepository repository;

    public MovimentacaoEstoque salvar(MovimentacaoEstoque entity) {
        return repository.save(entity);
    }

    public List<MovimentacaoEstoque> buscaTodos() {
        return repository.findAll();
    }
}