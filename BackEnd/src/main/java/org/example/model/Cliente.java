package org.example.model;

public class Cliente extends Usuario{
    //region Atributos

    private String cpf;
    private String rg;

    //endregion
//region metodos
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @Override
    public String getDocumentoPrincipal() {
        return this.getCpf();
    }
    //endregion
}
