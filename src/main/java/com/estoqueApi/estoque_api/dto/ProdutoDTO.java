package com.estoqueApi.estoque_api.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private int quantidade;
    private double preco;
}