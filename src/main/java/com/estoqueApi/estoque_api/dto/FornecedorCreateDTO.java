package com.estoqueApi.estoque_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// import jakarta.validation.constraints.NotBlank; // Descomente se você usa validações

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorCreateDTO {
    // @NotBlank(message = "O nome do fornecedor é obrigatório.") // Descomente e importe NotBlank se precisar
    private String nome;
    // @NotBlank(message = "O CNPJ é obrigatório.") // Descomente e importe NotBlank se precisar
    private String cnpj;
    // @NotBlank(message = "O telefone é obrigatório.") // Descomente e importe NotBlank se precisar
    private String telefone;
    // @NotBlank(message = "O e-mail é obrigatório.") // Descomente e importe NotBlank se precisar
    // @Email(message = "Formato de e-mail inválido.") // Descomente e importe Email se precisar
    private String email;
}