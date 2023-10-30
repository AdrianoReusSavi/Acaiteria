package org.example.service;

import org.example.model.UnidadeMedida;
import org.example.repository.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeMedidaService {
    @Autowired
    private UnidadeMedidaRepository repository;

    public List<UnidadeMedida> buscaTodos() {
        return repository.findAll();
    }
}
