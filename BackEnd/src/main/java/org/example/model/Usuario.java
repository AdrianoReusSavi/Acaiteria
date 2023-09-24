package org.example.model;

public abstract class Usuario extends EntityId{
    //region atributos
    private String nome;
    //endregion

    //region metodos
    public abstract String getDocumentoPrincipal();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    //endregion
}
