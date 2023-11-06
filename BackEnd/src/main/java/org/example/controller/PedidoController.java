package org.example.controller;

import org.example.dto.PedidoDTO;
import org.example.model.Item;
import org.example.model.MovimentacaoEstoque;
import org.example.model.Pedido;
import org.example.model.PedidoItem;
import org.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

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
    public ResponseEntity<Pedido> create(@RequestBody @Valid Pedido entity) {
        Pedido save = pedidoService.salvar(entity);

        for(PedidoItem pedidoItem : entity.getPedidoItens()) {
            pedidoItem.setPedido(save);
            pedidoItemService.salvar(pedidoItem);

            Long itemId = pedidoItem.getItem().getId();
            Item item = itemService.buscaPorId(itemId);

            Integer quantidadePedido = pedidoItem.getQuantidade();
            item.setQuantidadeEstoque(item.getQuantidadeEstoque() - quantidadePedido);
            itemService.salvar(item);

            movimentacaoEstoqueService.salvarMovimentacao(pedidoItem.getItem(), pedidoItem.getQuantidade(), "s", pedidoItem.getValorVenda());
        }

        return  ResponseEntity.created(URI.create("/api/pedido/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity<Page<PedidoDTO>> findAll(@RequestParam(required = false) String filter,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "15") int size) {
        Page<Pedido> pedidos = pedidoService.buscaTodos(filter, PageRequest.of(page, size));
        Page<PedidoDTO> pedidoDTOS = PedidoDTO.fromEntity(pedidos);
        return ResponseEntity.ok(pedidoDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pedido> findById(@PathVariable("id") Long id) {
        Pedido pedido = pedidoService.buscaPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Pedido> remove(@PathVariable("id") Long id) {
        pedidoService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Pedido> update(@PathVariable("id") Long id, @RequestBody Pedido entity) {
        try {
            Pedido alterado = pedidoService.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        }
        catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}

//region antiga movimentacao
//MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
//movimentacao.setItem(pedidoItem.getItem());
//movimentacao.setQuantidade_movimento(-pedidoItem.getQuantidade());
//movimentacao.setData_hora(save.getData_hora());
//movimentacao.setTipo("s");
//movimentacao.setValor(pedidoItem.getValor_venda());
//movimentacaoEstoqueService.salvar(movimentacao);
//endregion