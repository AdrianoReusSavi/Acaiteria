package org.example.controller;

import org.example.dto.PedidoItemDTO;
import org.example.model.PedidoItem;
import org.example.service.NotFoundException;
import org.example.service.PedidoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/pedidoItem")
@CrossOrigin(origins = "http://localhost:3000")
public class PedidoItemController extends AbstractController {
    @Autowired
    private PedidoItemService service;

    @PostMapping
    public ResponseEntity<PedidoItem> create(@RequestBody @Valid PedidoItem entity) {
        PedidoItem save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/pedidoItem/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity<Page<PedidoItemDTO>> findAll(@RequestParam(required = false) String filter,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "15") int size) {
        Page<PedidoItem> pedidoItems = service.buscaTodos(filter, PageRequest.of(page, size));
        Page<PedidoItemDTO> pedidoItemDTOS = PedidoItemDTO.fromEntity(pedidoItems);
        return ResponseEntity.ok(pedidoItemDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoItem> findById(@PathVariable("id") Long id) {
        PedidoItem pedidoItem = service.buscaPorId(id);
        return ResponseEntity.ok(pedidoItem);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PedidoItem> remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
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