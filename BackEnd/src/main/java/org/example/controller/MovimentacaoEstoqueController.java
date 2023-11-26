package org.example.controller;

import org.example.dto.MovimentacaoEstoqueDTO;
import org.example.model.MovimentacaoEstoque;
import org.example.service.MovimentacaoEstoqueService;
import org.example.service.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/movimentacaoEstoque")
@CrossOrigin(origins = "http://localhost:3000")
public class MovimentacaoEstoqueController extends AbstractController {
    @Autowired
    private MovimentacaoEstoqueService service;

    @PostMapping
    public ResponseEntity<MovimentacaoEstoque> create(@RequestBody @Valid MovimentacaoEstoque entity) {
        MovimentacaoEstoque save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/movimentacaoEstoque/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity<Page<MovimentacaoEstoqueDTO>> findAll(@RequestParam(required = false) String filter,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "15") int size) {
        Page<MovimentacaoEstoque> movimentacaoEstoques = service.buscaTodos(filter, PageRequest.of(page, size));
        Page<MovimentacaoEstoqueDTO> movimentacaoEstoqueDTOS = MovimentacaoEstoqueDTO.fromEntity(movimentacaoEstoques);
        return ResponseEntity.ok(movimentacaoEstoqueDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity<MovimentacaoEstoque> findById(@PathVariable("id") Long id) {
        MovimentacaoEstoque movimentacaoEstoque = service.buscaPorId(id);
        return ResponseEntity.ok(movimentacaoEstoque);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MovimentacaoEstoque> remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<MovimentacaoEstoque> update(@PathVariable("id") Long id, @RequestBody MovimentacaoEstoque entity) {
        try {
            MovimentacaoEstoque alterado = service.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        }
        catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}