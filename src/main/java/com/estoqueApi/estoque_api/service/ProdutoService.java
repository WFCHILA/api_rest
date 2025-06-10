package com.estoqueApi.estoque_api.service;


import com.estoqueApi.estoque_api.dto.ProdutoDTO;
import com.estoqueApi.estoque_api.model.Produto;
import com.estoqueApi.estoque_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<ProdutoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO salvar(ProdutoDTO dto) {
        Produto produto = toEntity(dto);
        return toDTO(repository.save(produto));
    }

    private Produto toEntity(ProdutoDTO dto) {
        return new Produto(dto.getId(), dto.getNome(), dto.getDescricao(), dto.getQuantidade(), dto.getPreco());
    }

    private ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getQuantidade(), produto.getPreco());
    }
}