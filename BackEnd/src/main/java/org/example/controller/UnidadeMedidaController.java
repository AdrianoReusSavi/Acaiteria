package org.example.controller;

import org.example.dto.UnidadeMedidaDTO;
import org.example.model.UnidadeMedida;
import org.example.service.NotFoundException;
import org.example.service.UnidadeMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/unidadeMedida")
@CrossOrigin(origins = "http://localhost:3000")
public class UnidadeMedidaController extends AbstractController {
    @Autowired
    private UnidadeMedidaService service;

    @PostMapping
    public ResponseEntity<UnidadeMedida> create(@RequestBody @Valid UnidadeMedida entity) {
        UnidadeMedida save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/unidadeMedida/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity<Page<UnidadeMedidaDTO>> findAll(@RequestParam(required = false) String filter,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "15") int size) {
        Page<UnidadeMedida> unidadesMedida = service.buscaTodos(filter, PageRequest.of(page, size));
        Page<UnidadeMedidaDTO> unidadeMedidaDTOS = UnidadeMedidaDTO.fromEntity(unidadesMedida);
        return ResponseEntity.ok(unidadeMedidaDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity<UnidadeMedida> findById(@PathVariable("id") Long id) {
        UnidadeMedida unidadeMedida = service.buscaPorId(id);
        return ResponseEntity.ok(unidadeMedida);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UnidadeMedida> remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UnidadeMedida> update(@PathVariable("id") Long id, @RequestBody UnidadeMedida entity) {
        try {
            UnidadeMedida alterado = service.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        }
        catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}