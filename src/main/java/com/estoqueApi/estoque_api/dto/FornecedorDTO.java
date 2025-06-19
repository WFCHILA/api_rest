package com.estoqueApi.estoque_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
}