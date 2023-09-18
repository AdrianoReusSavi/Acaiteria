package org.example.model;

import java.time.LocalDate ;
import java.util.ArrayList;
import java.util.List;

public class Venda extends EntityId implements OperacaoFinanceira{
    //region atributos
    private LocalDate dataVenda;
    private TipoOperacao tipoOperacao;
    private String observacao;
    private Cliente cliente;
    private List<ItemVenda> itens = new ArrayList<>();
    public void additemVenda(ItemVenda item) {
        this.itens.add(item);
    }
    public void delItemVenda(ItemVenda item){
        this.itens.remove(item);
    }
    public List<ItemVenda> getItens(){
        return itens;
    }
    //endregion

    //region metodos
    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public TipoOperacao tipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(TipoOperacao tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    //endregion
    @Override
    public LocalDate getDataOperacao() {
        return this.getDataVenda();
    }

    @Override
    public Double getValorTotalOperacao() {
        return this.getItens().stream()
                .mapToDouble(ItemVenda::getValorCalculado)
                .sum();
    }

    @Override
    public TipoOperacao getTipoOperacao() {
        return TipoOperacao.CREDITO;
    }

    public void addItemVenda(ItemVenda item) {
        this.itens.add(item);
    }
}
