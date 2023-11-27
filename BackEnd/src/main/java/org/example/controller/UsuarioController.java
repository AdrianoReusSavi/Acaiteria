package org.example.controller;

import org.example.dto.UsuarioDTO;
import org.example.model.Usuario;
import org.example.service.NotFoundException;
import org.example.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController extends AbstractController {
    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody @Valid Usuario entity) {
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

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAll(@RequestParam(required = false) String filter,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "15") int size) {
        Page<Usuario> usuarios = service.buscaTodos(filter, PageRequest.of(page, size));
        Page<UsuarioDTO> usuarioDTOS = UsuarioDTO.fromEntity(usuarios);
        return ResponseEntity.ok(usuarioDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> findById(@PathVariable("id") Long id) {
        Usuario usuario = service.buscaPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") Long id, @RequestBody Usuario entity) {
        try {
            Usuario alterado = service.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        }
        catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}