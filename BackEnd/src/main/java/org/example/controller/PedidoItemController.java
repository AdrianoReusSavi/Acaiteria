package org.example.controller;

import org.example.model.PedidoItem;
import org.example.service.NotFoundException;
import org.example.service.PedidoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedidoItem")
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoItemController extends AbstractController {
    @Autowired
    private PedidoItemService service;

    @PostMapping
    public ResponseEntity<PedidoItem> create(@RequestBody PedidoItem entity) {
        PedidoItem save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/pedidoItem/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity<List<PedidoItem>> findAll() {
        List<PedidoItem> itens = service.buscaTodos();
        return ResponseEntity.ok(itens);
    }

    @PutMapping("{id}")
    public ResponseEntity<PedidoItem> update(@PathVariable("id") Long id, @RequestBody PedidoItem entity) {
        try {
            PedidoItem alterado = service.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        }
        catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}