package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Usuario extends EntityId {
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "senha")
    private String senha;
    @Column(name = "permissao")
    private Integer permissao;

    public Usuario() {

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getPermissao() {
        return permissao;
    }

    public void setPermissao(Integer permissao) {
        this.permissao = permissao;
    }
}
