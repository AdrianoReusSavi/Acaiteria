package org.example.service;

import org.example.model.Pedido;
import org.example.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PedidoRepository repository;

    public Pedido salvar(Pedido entity) {
        return repository.save(entity);
    }

    public List<Pedido> buscaTodos(String filter) {
        return repository.findAll(filter, Pedido.class);
    }

    public Page<Pedido> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, Pedido.class, pageable);
    }

    public Pedido buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Pedido alterar(Long id, Pedido entity) {
        Optional<Pedido> existingPedidoOptional = repository.findById(id);
        if(existingPedidoOptional.isEmpty()) {
            throw new NotFoundException("Pedido não encontrado!");
        }
        Pedido existingPedido = existingPedidoOptional.get();
        modelMapper.map(entity, existingPedido);
        return repository.save(existingPedido);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}