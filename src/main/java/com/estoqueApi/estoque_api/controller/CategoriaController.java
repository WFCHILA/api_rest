package com.estoqueApi.estoque_api.controller;

import com.estoqueApi.estoque_api.service.CategoriaService;
import com.estoqueApi.estoque_api.dto.CategoriaDTO;
import com.estoqueApi.estoque_api.dto.CategoriaCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoriaDTO> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criar(@RequestBody CategoriaCreateDTO dto) {
        CategoriaDTO categoriaCriada = service.criar(dto);
        return new ResponseEntity<>(categoriaCriada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaCreateDTO dto) {
        CategoriaDTO categoriaAtualizada = service.atualizar(id, dto);
        if (categoriaAtualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removida = service.deletar(id);
        return removida ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}