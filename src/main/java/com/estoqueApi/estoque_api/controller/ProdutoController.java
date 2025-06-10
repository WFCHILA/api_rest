package com.estoqueApi.estoque_api.controller;

import com.estoqueApi.estoque_api.dto.ProdutoDTO;
import com.estoqueApi.estoque_api.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProdutoDTO> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping
    public ProdutoDTO salvar(@RequestBody ProdutoDTO dto) {
        return service.salvar(dto);
    }
}