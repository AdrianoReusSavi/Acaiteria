package org.example.model;
import java.time.LocalDate;

public class Produto extends ItemVendavel {
    //region atributos


    private String nome;
    private Double precoCompra;
    private Status status;



//endregion

    //region construtores

    public Produto(String nome, String descricao) {
        this.nome = nome;
        super.setDescricao(descricao);
    }
    public Produto(String nome, Double precoVenda, Double precoCompra, Status status){
        this.nome = nome;
        super.setValorUnitario(precoVenda);
        this.precoCompra = precoCompra;
        this.status = status;
    }

    public Produto( String nome, String descricao,Double precoVenda, Double precoCompra, LocalDate dataValidade, LocalDate dataPrazo, Status status) {
        this.nome = nome;
        super.setDescricao(descricao);
        super.setValorUnitario(precoVenda);
        this.precoCompra = precoCompra;
        this.status = status;
    }
    //endregion

    //region getters e setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getPrecoCompra() {
        return precoCompra;
    }
    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    //endregion
    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", precoCompra=" + precoCompra +
                ", status=" + status +
                '}';
    }
}

