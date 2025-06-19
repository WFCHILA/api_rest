package com.estoqueApi.estoque_api.controller;

import com.estoqueApi.estoque_api.dto.FuncionarioCreateDTO;
import com.estoqueApi.estoque_api.dto.FuncionarioDTO;
import com.estoqueApi.estoque_api.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<FuncionarioDTO> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> criar(@RequestBody FuncionarioCreateDTO dto) {
        FuncionarioDTO funcionarioCriado = service.criar(dto);
        return new ResponseEntity<>(funcionarioCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> atualizar(@PathVariable Long id, @RequestBody FuncionarioCreateDTO dto) {
        // Changed FuncionarioDTO to FuncionarioCreateDTO in @RequestBody
        FuncionarioDTO funcionarioAtualizado = service.atualizar(id, dto);
        if (funcionarioAtualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(funcionarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = service.deletar(id);
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}