package com.estoqueApi.estoque_api.service;

import com.estoqueApi.estoque_api.repository.FuncionarioRepository;
import com.estoqueApi.estoque_api.dto.FuncionarioDTO;
import com.estoqueApi.estoque_api.dto.FuncionarioCreateDTO;
import com.estoqueApi.estoque_api.model.Funcionario;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    // @Autowired não é estritamente necessário em construtores a partir do Spring 4.3+ com uma única dependência
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public FuncionarioDTO criar(FuncionarioCreateDTO dto) {
        Funcionario f = new Funcionario();
        f.setNome(dto.getNome());
        f.setCargo(dto.getCargo());
        f.setSalario(dto.getSalario());
        // Adicione aqui se FuncionarioCreateDTO tiver email e você quiser salvá-lo
        // f.setEmail(dto.getEmail()); // Se FuncionarioCreateDTO e Funcionario tiverem email

        Funcionario savedFuncionario = funcionarioRepository.save(f); // Capture o resultado da persistência

        return toDTO(savedFuncionario); // Use o objeto salvo para criar o DTO
    }

    public List<FuncionarioDTO> listarTodos() {
        return funcionarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // --- MÉTODO ATUALIZAR CORRIGIDO ---
    public FuncionarioDTO atualizar(Long id, FuncionarioCreateDTO dto) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);

        if (funcionarioOptional.isPresent()) {
            Funcionario f = funcionarioOptional.get();
            f.setNome(dto.getNome());
            f.setCargo(dto.getCargo());
            f.setSalario(dto.getSalario());
            // Adicione aqui se FuncionarioCreateDTO tiver email e você quiser atualizar
            // f.setEmail(dto.getEmail()); // Se FuncionarioCreateDTO e Funcionario tiverem email

            Funcionario updatedFuncionario = funcionarioRepository.save(f); // Salve a entidade atualizada
            return toDTO(updatedFuncionario); // Retorne o DTO da entidade atualizada
        } else {
            return null; // Retorne null se o funcionário não for encontrado, como esperado pelo Controller
        }
    }

    public boolean deletar(Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private FuncionarioDTO toDTO(Funcionario f) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(f.getId());
        dto.setNome(f.getNome());
        dto.setCargo(f.getCargo());
        dto.setSalario(f.getSalario());
        return dto;
    }
}