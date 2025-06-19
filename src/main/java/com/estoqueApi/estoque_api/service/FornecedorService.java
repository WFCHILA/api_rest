package com.estoqueApi.estoque_api.service;

import com.estoqueApi.estoque_api.repository.FornecedorRepository;
import com.estoqueApi.estoque_api.dto.FornecedorDTO;
import com.estoqueApi.estoque_api.dto.FornecedorCreateDTO;
import com.estoqueApi.estoque_api.model.Fornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional; // Mantenha este import
import java.util.stream.Collectors;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public FornecedorDTO criar(FornecedorCreateDTO dto) {
        Fornecedor fornecedor = convertToEntity(dto);
        fornecedorRepository.save(fornecedor);
        return convertToDTO(fornecedor);
    }

    public List<FornecedorDTO> listarTodos() {
        return fornecedorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // --- MÉTODO ATUALIZAR CORRIGIDO ---
    public FornecedorDTO atualizar(Long id, FornecedorCreateDTO dto) {
        // Use findById para obter um Optional
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);

        // Verifique se o fornecedor existe
        if (fornecedorOptional.isPresent()) {
            Fornecedor fornecedor = fornecedorOptional.get(); // Obtenha a entidade real do Optional
            fornecedor.setNome(dto.getNome());
            fornecedor.setEmail(dto.getEmail());
            fornecedor.setTelefone(dto.getTelefone());
            // Se tiver o CNPJ no FornecedorCreateDTO e quiser atualizar, adicione:
            // fornecedor.setCnpj(dto.getCnpj());

            Fornecedor updatedFornecedor = fornecedorRepository.save(fornecedor); // Salve a entidade atualizada
            return convertToDTO(updatedFornecedor); // Converta e retorne o DTO
        } else {
            return null; // Retorna null se o fornecedor não for encontrado
        }
    }

    public boolean deletar(Long id) {
        if (fornecedorRepository.existsById(id)) {
            fornecedorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private FornecedorDTO convertToDTO(Fornecedor fornecedor) {
        FornecedorDTO dto = new FornecedorDTO();
        dto.setId(fornecedor.getId());
        dto.setNome(fornecedor.getNome());
        dto.setEmail(fornecedor.getEmail());
        dto.setTelefone(fornecedor.getTelefone());
        // Se FornecedorDTO tiver CNPJ, adicione:
        // dto.setCnpj(fornecedor.getCnpj());
        return dto;
    }

    private Fornecedor convertToEntity(FornecedorCreateDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.getNome());
        fornecedor.setEmail(dto.getEmail());
        fornecedor.setTelefone(dto.getTelefone());
        // Se FornecedorCreateDTO tiver CNPJ, adicione:
        // fornecedor.setCnpj(dto.getCnpj());
        return fornecedor;
    }
}