package org.example.controller;

import org.example.model.Item;
import org.example.service.ItemService;
import org.example.service.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/item")
@CrossOrigin(origins = "http://localhost:5173")
public class ItemController extends AbstractController {
    @Autowired
    private ItemService service;

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item entity) {
        Item save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/item/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        List<Item> itens = service.buscaTodos();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/listaAtivos")
    public ResponseEntity<List<Item>> findAllAtivos() {
        List<Item> itens = service.buscaTodosAtivos();
        return ResponseEntity.ok(itens);
    }

    @PutMapping("{id}")
    public ResponseEntity<Item> update(@PathVariable("id") Long id, @RequestBody Item entity) {
        try {
            Item alterado = service.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        }
        catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}