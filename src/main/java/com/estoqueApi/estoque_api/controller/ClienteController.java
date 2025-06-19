package com.estoqueApi.estoque_api.controller;

import com.estoqueApi.estoque_api.service.ClienteService;
import com.estoqueApi.estoque_api.dto.ClienteDTO;
import com.estoqueApi.estoque_api.dto.ClienteCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteDTO> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@RequestBody ClienteCreateDTO dto) {
        ClienteDTO clienteCriado = service.criar(dto);
        return new ResponseEntity<>(clienteCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody ClienteCreateDTO dto) {
        ClienteDTO clienteAtualizado = service.atualizar(id, dto);
        if (clienteAtualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = service.deletar(id);
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
