package org.example.service;

import org.example.model.Item;
import org.example.model.PedidoItem;
import org.example.model.TipoMovimentacao;
import org.example.repository.PedidoItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoItemService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PedidoItemRepository repository;

    @Autowired
    private MovimentacaoEstoqueService movimentacaoEstoqueService;

    @Autowired
    private ItemService itemService;

    public PedidoItem salvar(PedidoItem entity) {
        Item item = itemService.buscaPorId(entity.getItem().getId());
        item.setQuantidadeEstoque(item.getQuantidadeEstoque() - entity.getQuantidade());
        if(item.getQuantidadeEstoque() < 0) {
            throw new ValidationException("Quantidade solicitada é maior do que o estoque!");
        }
        itemService.alterar(item.getId(), item);
        movimentacaoEstoqueService.salvarMovimentacao(entity.getItem(), entity.getQuantidade(), TipoMovimentacao.SAIDA, entity.getValorVenda());
        return repository.save(entity);
    }

    public PedidoItem salvarByPedido(PedidoItem entity) {
        return repository.save(entity);
    }

    public List<PedidoItem> buscaTodos(String filter) {
        return repository.findAll(filter, PedidoItem.class);
    }

    public Page<PedidoItem> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, PedidoItem.class, pageable);
    }

    public PedidoItem buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public PedidoItem alterar(Long id, PedidoItem entity) {
        Optional<PedidoItem> existingPedidoItemOptional = repository.findById(id);
        if(existingPedidoItemOptional.isEmpty()) {
            throw new NotFoundException("Item do pedido não encontrado!");
        }
        PedidoItem existingPedidoItem = existingPedidoItemOptional.get();
        modelMapper.map(entity, existingPedidoItem);
        return repository.save(existingPedidoItem);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}