package org.example.service;

import org.example.model.Pedido;
import org.example.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PedidoRepository repository;

    public Pedido salvar(Pedido entity) {
        return repository.save(entity);
    }

    public List<Pedido> buscaTodos() {
        return repository.findAll();
    }

    public Pedido findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
    }
}
