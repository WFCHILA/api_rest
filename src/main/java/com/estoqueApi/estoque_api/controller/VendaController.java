package com.estoqueApi.estoque_api.controller;

import com.estoqueApi.estoque_api.model.Venda;
import com.estoqueApi.estoque_api.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.estoqueApi.estoque_api.dto.VendaRequestDTO;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/processar")
    public ResponseEntity<Venda> processarVenda(@RequestBody VendaRequestDTO vendaRequestDTO) {

        Venda novaVenda = vendaService.processarVenda(vendaRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
    }

}