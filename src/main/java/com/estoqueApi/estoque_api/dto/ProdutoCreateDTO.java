package com.estoqueApi.estoque_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoCreateDTO {
    private String nome;
    private String descricao;
    private int quantidade; // Considere usar Integer para permitir valores nulos, se necessário
    private double preco;  // Considere usar Double para permitir valores nulos, se necessário
}