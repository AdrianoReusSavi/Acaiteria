package org.example.seeder;

import org.example.model.QUsuario;
import org.example.model.Usuario;
import org.example.repository.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UsuarioSeeder implements CommandLineRunner {
    private final UsuarioRepository repository;

    @Autowired
    public UsuarioSeeder(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        //adicionarNovoUsuario("admin", "admin", "123", 0);
    }

    private void adicionarNovoUsuario(String nome, String login, String senha, Integer permissao) {
        QUsuario qUsuario = QUsuario.usuario;
        boolean existe = repository.exists(
                qUsuario.login.eq(nome)
                        .and(qUsuario.login.eq(login))
        );

        if(!existe) {
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));
            usuario.setPermissao(permissao);
            repository.save(usuario);
        }
    }
}
