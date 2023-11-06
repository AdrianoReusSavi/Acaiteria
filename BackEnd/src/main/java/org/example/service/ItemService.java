package org.example.service;

import org.example.model.Item;
import org.example.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemRepository repository;

    public Item salvar(Item entity) {
        return repository.save(entity);
    }

    public List<Item> buscaTodos(String filter) {
        return repository.findAll(filter, Item.class);
    }

    public Page<Item> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, Item.class, pageable);
    }

    public Item buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Item alterar(Long id, Item entity) {
        Optional<Item> existingItemOptional = repository.findById(id);
        if(existingItemOptional.isEmpty()) {
            throw new NotFoundException("Item não encontrado!");
        }
        Item existingItem = existingItemOptional.get();
        modelMapper.map(entity, existingItem);
        return repository.save(existingItem);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}