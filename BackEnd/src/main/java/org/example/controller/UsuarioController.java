package org.example.controller;

import org.example.model.Usuario;
import org.example.service.NotFoundException;
import org.example.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController extends AbstractController {
    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity create(@RequestBody Usuario entity) {
        entity.setId(null);
        Usuario save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/usuario/" + entity.getId())).body(save);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario entity) {
        String username = entity.getLogin();
        String password = entity.getSenha();
        Usuario usuario = service.findByUserAndPassword(username, password);
        if(usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/lista")
    public ResponseEntity findAll() {
        List<Usuario> usuarios = service.buscaTodos();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Usuario entity) {
        try {
            Usuario alterado = service.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        }
        catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}