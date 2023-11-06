package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Usuario extends EntityId {
    @Column(name = "nome", nullable = false, length = 60)
    private String nome;
    @Column(name = "login", nullable = false, length = 60)
    private String login;
    @Column(name = "senha", nullable = false, length = 20)
    private String senha;
    @Column(name = "permissao", nullable = false)
    private Integer permissao;

    //region Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
    //endregion
}