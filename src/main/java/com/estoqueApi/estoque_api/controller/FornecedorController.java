package com.estoqueApi.estoque_api.controller;

import com.estoqueApi.estoque_api.service.FornecedorService;
import com.estoqueApi.estoque_api.dto.FornecedorDTO;
import com.estoqueApi.estoque_api.dto.FornecedorCreateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @GetMapping
    public List<FornecedorDTO> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<FornecedorDTO> criar(@RequestBody FornecedorCreateDTO dto) {
        FornecedorDTO fornecedorCriado = service.criar(dto);
        return new ResponseEntity<>(fornecedorCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorDTO> atualizar(@PathVariable Long id, @RequestBody FornecedorCreateDTO dto) {
        FornecedorDTO fornecedorAtualizado = service.atualizar(id, dto);
        if (fornecedorAtualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fornecedorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = service.deletar(id);
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}