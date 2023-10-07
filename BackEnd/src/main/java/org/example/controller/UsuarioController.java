package org.example.controller;

import org.example.model.Usuario;
import org.example.repository.UsuarioRepository;
import org.example.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService service;

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    @GetMapping("/login")
    public ResponseEntity<Usuario> login(@RequestParam("user") String user, @RequestParam("password") String password) {
        Usuario usuario = service.findByUserAndPassword(user, password);
        if(usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
