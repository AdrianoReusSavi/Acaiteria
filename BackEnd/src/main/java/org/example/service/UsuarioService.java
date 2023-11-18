package org.example.service;

import org.example.model.QUsuario;
import org.example.model.Usuario;
import org.example.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        //region Regras de negócio
        validaUsuario(entity.getLogin(), 0L);
        //endregion
        return repository.save(entity);
    }

    public List<Usuario> buscaTodos(String filter) {
        return repository.findAll(filter, Usuario.class);
    }

    public Page<Usuario> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, Usuario.class, pageable);
    }

    public Usuario buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Usuario alterar(Long id, Usuario entity) {
        Optional<Usuario> existingUsuarioOptional = repository.findById(id);
        if(existingUsuarioOptional.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }
        Usuario existingUsuario = existingUsuarioOptional.get();
        modelMapper.map(entity, existingUsuario);
        //region Regras de negócio
        validaUsuario(entity.getLogin(), id);
        //endregion
        return repository.save(existingUsuario);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public Usuario findByUserAndPassword(String user, String password) {
        return repository.findByUserAndPassword(user, password);
    }

    private void validaUsuario(String login, Long id) {
        boolean existe = repository.exists(QUsuario.usuario.id.ne(id).and(QUsuario.usuario.login.eq(login)));
        if(existe) {
            throw new ValidationException("Login já existente no sistema!");
        }
    }
}