package org.example.controller;

import org.example.model.UnidadeMedida;
import org.example.service.UnidadeMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/unidadeMedida")
@CrossOrigin()
public class UnidadeMedidaController extends AbstractController {
    @Autowired
    private UnidadeMedidaService service;

    @GetMapping
    public ResponseEntity<List<UnidadeMedida>> findAll() {
        List<UnidadeMedida> unidadesMedida = service.buscaTodos();
        return ResponseEntity.ok(unidadesMedida);
    }
}