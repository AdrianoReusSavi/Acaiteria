package org.example.controller;

import org.example.model.Item;
import org.example.model.MovimentacaoEstoque;
import org.example.model.Pedido;
import org.example.model.PedidoItem;
import org.example.service.ItemService;
import org.example.service.MovimentacaoEstoqueService;
import org.example.service.PedidoItemService;
import org.example.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedido")
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoController extends AbstractController {
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoItemService pedidoItemService;

    @Autowired
    private MovimentacaoEstoqueService movimentacaoEstoqueService;

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody Pedido entity) {
        Pedido save = pedidoService.salvar(entity);

        for(PedidoItem pedidoItem : entity.getPedidoItens()) {
            pedidoItem.setPedido(save);
            pedidoItemService.salvar(pedidoItem);

            Long itemId = pedidoItem.getItem().getId();
            Item item = itemService.buscaPorId(itemId);

            Double quantidadePedido = pedidoItem.getQuantidade();
            item.setQuantidade_estoque(item.getQuantidade_estoque() - quantidadePedido);
            itemService.salvar(item);

            MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
            movimentacao.setItem(pedidoItem.getItem());
            movimentacao.setQuantidade_movimento(-pedidoItem.getQuantidade());
            //movimentacao.setTipo();
            movimentacao.setValor(pedidoItem.getValor_venda());
            movimentacaoEstoqueService.salvar(movimentacao);
        }

        return  ResponseEntity.created(URI.create("/api/pedido/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> itens = pedidoService.buscaTodos();
        return ResponseEntity.ok(itens);
    }
}