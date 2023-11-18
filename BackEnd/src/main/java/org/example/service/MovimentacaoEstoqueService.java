package org.example.service;

import org.example.model.Item;
import org.example.model.MovimentacaoEstoque;
import org.example.model.TipoMovimentacao;
import org.example.repository.MovimentacaoEstoqueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoEstoqueService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovimentacaoEstoqueRepository repository;

    public MovimentacaoEstoque salvar(MovimentacaoEstoque entity) {
        return repository.save(entity);
    }

    public List<MovimentacaoEstoque> buscaTodos(String filter) {
        return repository.findAll(filter, MovimentacaoEstoque.class);
    }

    public Page<MovimentacaoEstoque> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, MovimentacaoEstoque.class, pageable);
    }

    public MovimentacaoEstoque buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public MovimentacaoEstoque alterar(Long id, MovimentacaoEstoque entity) {
        Optional<MovimentacaoEstoque> existingMovimentacaoEstoqueOptional = repository.findById(id);
        if(existingMovimentacaoEstoqueOptional.isEmpty()) {
            throw new NotFoundException("Movimentação Estoque não encontrada!");
        }
        MovimentacaoEstoque existingMovimentacaoEstoque = existingMovimentacaoEstoqueOptional.get();
        modelMapper.map(entity, existingMovimentacaoEstoque);
        return repository.save(existingMovimentacaoEstoque);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public void salvarMovimentacao(Item item, Integer diferenca, TipoMovimentacao tipo, Double valor) {
        if(diferenca < 0) {
            throw new ValidationException("Quantidade não pode ser menor que zero!");
        }
        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
        movimentacaoEstoque.setItem(item);
        movimentacaoEstoque.setQuantidadeMovimento(diferenca);
        movimentacaoEstoque.setDataHora(LocalDateTime.now());
        movimentacaoEstoque.setTipo(tipo);
        movimentacaoEstoque.setValor(valor);
        salvar(movimentacaoEstoque);
    }
}