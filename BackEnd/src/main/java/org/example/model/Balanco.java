package org.example.model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Balanco extends EntityId {
        private LocalDate dataBalanco;
        private String responsavel;
        private List<OperacaoFinanceira> operacoes = new ArrayList<>();


        //region getter e setter
        public LocalDate getDataBalanco() {
                return dataBalanco;
        }

        public void setDataBalanco(LocalDate dataBalanco) {
                this.dataBalanco = dataBalanco;
        }

        public String getResponsavel() {
                return responsavel;
        }

        public void setResponsavel(String responsavel) {
                this.responsavel = responsavel;
        }

        public List<OperacaoFinanceira> getOperacoes() {
                return operacoes;
        }

        public void setOperacoes(List<OperacaoFinanceira> operacoes) {
                this.operacoes = operacoes;
        }

        public String getTipoOperacao(OperacaoFinanceira operacao) {
                return "Compra";
        }
//endregion

                public void addOperacoes (OperacaoFinanceira operacao){
                        this.operacoes.add(operacao);
                }
                public Double getvalorTotal (TipoOperacao tipo){
                        return this.getOperacoes().stream().filter(op -> op.getTipoOperacao().equals(tipo))
                                .mapToDouble(OperacaoFinanceira::getValorTotalOperacao)
                                .sum();
                }
                public void imprimirBalanco () {
                        System.out.println("------------------------------------");
                        System.out.println("Balanço numero: " + this.getId());
                        System.out.println("Data: " + this.getDataBalanco());
                        System.out.println("Responsavel: " + this.getResponsavel());
                        System.out.println("------------------------------------");
                        System.out.println("Itens: ");
                        for (OperacaoFinanceira op : this.getOperacoes()) {
                                System.out.println(" Data operação: " + op.getDataOperacao()
                                        + " Tipo operação: " + op.getTipoOperacao()
                                        + " Valor operação: " + op.getValorTotalOperacao() +
                                        " - (" + getTipoOperacao(op) + ")");
                        }
                        System.out.println("------------------------------------");
                        System.out.println("Total Debitos: " + this.getvalorTotal(TipoOperacao.DEBITO));
                        System.out.println("Total Creditos: " + this.getvalorTotal(TipoOperacao.CREDITO));
                        System.out.println("Total: " + (this.getvalorTotal(TipoOperacao.CREDITO)
                                - this.getvalorTotal(TipoOperacao.DEBITO)));
        }
}
