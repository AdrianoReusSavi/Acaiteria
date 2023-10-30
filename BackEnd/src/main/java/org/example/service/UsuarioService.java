package org.example.service;

import org.example.model.QUsuario;
import org.example.model.Usuario;
import org.example.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario entity) {
        if(!repository.findAll(QUsuario.usuario.nome.eq(entity.getNome())).isEmpty()) {
            throw new ValidationException("Já existe um usuário com essa descrição cadastrado!");
        }
        return repository.save(entity);
    }

    public List<Usuario> buscaTodos() {
        return repository.findAll();
    }

    public Usuario findByUserAndPassword(String user, String password) {
        return repository.findByUserAndPassword(user, password);
    }

    public Usuario alterar(Long id, Usuario entity) {
        Optional<Usuario> existingUsuarioOptional = repository.findById(id);
        if(existingUsuarioOptional.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }

        Usuario existingUsuario = existingUsuarioOptional.get();
        modelMapper.map(entity, existingUsuario);

        return repository.save(existingUsuario);
    }
}
