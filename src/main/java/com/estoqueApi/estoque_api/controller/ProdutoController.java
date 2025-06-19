package com.estoqueApi.estoque_api.controller;

import com.estoqueApi.estoque_api.dto.ProdutoDTO;
import com.estoqueApi.estoque_api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<ProdutoDTO> listarTodos() {
        return produtoService.listarTodos();
    }

    @PostMapping
    public ProdutoDTO salvar(@RequestBody ProdutoDTO dto) {
        return produtoService.salvar(dto);
    }

    @PutMapping("/{id}")
    public ProdutoDTO atualizar(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        return produtoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }
}
