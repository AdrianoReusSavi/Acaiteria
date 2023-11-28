package org.example.model;

public class Fechamento {
    private String categoria;
    private Double totalCategoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getTotalCategoria() {
        return totalCategoria;
    }

    public void setTotalCategoria(Double totalCategoria) {
        this.totalCategoria = totalCategoria;
    }

    public Fechamento(String categoria, Double totalCategoria) {
        this.categoria = categoria;
        this.totalCategoria = totalCategoria;
    }
}