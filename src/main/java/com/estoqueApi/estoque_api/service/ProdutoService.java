package com.estoqueApi.estoque_api.service;

import com.estoqueApi.estoque_api.repository.ProdutoRepository; 
import com.estoqueApi.estoque_api.dto.ProdutoDTO;         
import com.estoqueApi.estoque_api.dto.ProdutoCreateDTO;   
import com.estoqueApi.estoque_api.model.Produto;         
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;             
import java.util.List;                     
import java.util.Optional;  
import java.util.stream.Collectors;
@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // Listar todos os produtos convertidos para DTO
    public List<ProdutoDTO> listarTodos() {
        List<Produto> produtos = repository.findAll();
        return produtos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // Salvar um novo produto a partir de DTO
    public ProdutoDTO salvar(ProdutoDTO dto) {
        Produto produto = new Produto(dto.getNome(), dto.getDescricao(), dto.getQuantidade(), dto.getPreco());
        Produto salvo = repository.save(produto);
        return converterParaDTO(salvo);
    }

    // Atualizar produto existente
    public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
        return repository.findById(id)
                .map(produto -> {
                    produto.setNome(dto.getNome());
                    produto.setDescricao(dto.getDescricao());
                    produto.setQuantidade(dto.getQuantidade());
                    produto.setPreco(dto.getPreco());
                    Produto atualizado = repository.save(produto);
                    return converterParaDTO(atualizado);
                }).orElse(null);
    }

    // Deletar produto por ID
    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Converter entidade Produto em ProdutoDTO
    private ProdutoDTO converterParaDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getQuantidade(),
                produto.getPreco()
        );
    }
}
