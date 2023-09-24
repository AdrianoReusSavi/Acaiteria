package org.example;

import org.example.model.*;

import java.time.LocalDate;

public class Application {

    public static void main(String[] args) {
        //Declaração Cliente
        Cliente cliente = new Cliente();
        cliente.setNome("gabriel");


        //Declaração de Produto
        Produto produto = new Produto("Acai", "1L doce");


        //Declaração de Compras
        Compra compra = new Compra();
        compra.setDataCompra(LocalDate.now());
        ItemCompra itemC1 = new ItemCompra(produto, 10.00, 10.0, 2.0);
        compra.addItemCompra(itemC1);


        //Declaração de Vendas
        Venda venda = new Venda();
        venda.setDataVenda(LocalDate.now());
        venda.setCliente(cliente);
        ItemVenda item = new ItemVenda(produto, 1500.00, 1.0, 10.0);
        venda.addItemVenda(item);


        Venda venda2 = new Venda();
        venda2.setDataVenda(LocalDate.now());
        venda2.setCliente(cliente);
        ItemVenda item2 = new ItemVenda(produto, 1500.00, 1.0, 10.0);
        venda2.addItemVenda(item2);


        //Balanco
        Balanco balanco = new Balanco();
        balanco.setId(352578L);
        balanco.setDataBalanco(LocalDate.now());
        balanco.setResponsavel("Leonardo");
        balanco.addOperacoes(venda);
        balanco.addOperacoes(venda2);
        balanco.addOperacoes(compra);

        balanco.imprimirBalanco();

    }

}
