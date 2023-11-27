package org.example.controller;

import org.example.dto.ItemDTO;
import org.example.model.Item;
import org.example.model.TipoMovimentacao;
import org.example.service.ItemService;
import org.example.service.MovimentacaoEstoqueService;
import org.example.service.NotFoundException;
import org.example.service.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;

@RestController
@RequestMapping("/api/item")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController extends AbstractController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private MovimentacaoEstoqueService movimentacaoEstoqueService;

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody @Valid Item entity) {
        Item save = itemService.salvar(entity);
        movimentacaoEstoqueService.salvarMovimentacao(entity, entity.getQuantidadeEstoque(), TipoMovimentacao.ENTRADA, entity.getPrecoCompra() * entity.getQuantidadeEstoque());
        return ResponseEntity.created(URI.create("/api/item/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity<Page<ItemDTO>> findAll(@RequestParam(required = false) String filter,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "15") int size) {
        Page<Item> itens = itemService.buscaTodos(filter, PageRequest.of(page, size));
        Page<ItemDTO> itemDTOS = ItemDTO.fromEntity(itens);
        return ResponseEntity.ok(itemDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> findById(@PathVariable("id") Long id) {
        Item item = itemService.buscaPorId(id);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Item> remove(@PathVariable("id") Long id) {
        itemService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Item> update(@PathVariable("id") Long id, @RequestBody Item entity) {
        try {
            Integer quantidade = itemService.buscaPorId(id).getQuantidadeEstoque();
            if (entity.getQuantidadeEstoque() >= quantidade)
            {
                Item alterado = itemService.alterar(id, entity);

                if(!quantidade.equals(alterado.getQuantidadeEstoque())) {
                    Integer diferenca = alterado.getQuantidadeEstoque() - quantidade;
                    Double valor = alterado.getPrecoCompra() * diferenca;
                    movimentacaoEstoqueService.salvarMovimentacao(alterado, diferenca, TipoMovimentacao.ENTRADA, valor);
                }
                return ResponseEntity.ok().body(alterado);
            }
            else{
                throw new ValidationException("Não é possível adicionar menos itens que a quantidade atual do estoque.");
            }
        }
        catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}

//region antiga movimentacao
//MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
//movimentacao.setItem(alterado);
//movimentacao.setQuantidade_movimento(Math.abs(quantidade - alterado.getQuantidade_estoque()));
//movimentacao.setData_hora(new Date());
//movimentacao.setTipo("e");
//movimentacao.setValor(alterado.getPreco_compra() * Math.abs(quantidade - alterado.getQuantidade_estoque()));
//movimentacaoEstoqueService.salvar(movimentacao);
//endregion