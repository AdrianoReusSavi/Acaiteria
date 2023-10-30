package org.example.service;

import org.example.model.PedidoItem;
import org.example.repository.PedidoItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoItemService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PedidoItemRepository repository;

    public PedidoItem salvar(PedidoItem entity) {
        return repository.save(entity);
    }

    public List<PedidoItem> buscaTodos() {
        return repository.findAll();
    }

    public PedidoItem alterar(Long id, PedidoItem entity) {
        Optional<PedidoItem> existingPedidoItemOptional = repository.findById(id);
        if(existingPedidoItemOptional.isEmpty()) {
            throw new NotFoundException("Item não encontrado!");
        }

        PedidoItem existingPedidoItem = existingPedidoItemOptional.get();
        modelMapper.map(entity, existingPedidoItem);

        return repository.save(existingPedidoItem);
    }
}
