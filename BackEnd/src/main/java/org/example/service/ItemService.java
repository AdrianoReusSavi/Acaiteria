package org.example.service;

import org.example.model.Item;
import org.example.model.QItem;
import org.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;

    public List<Item> findItens(long id) {
        return repository.findAll(QItem.item.id.eq(id));
    }
}
