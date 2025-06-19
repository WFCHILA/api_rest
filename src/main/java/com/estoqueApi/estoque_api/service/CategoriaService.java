package com.estoqueApi.estoque_api.service;

import com.estoqueApi.estoque_api.repository.CategoriaRepository;
import com.estoqueApi.estoque_api.dto.CategoriaDTO;
import com.estoqueApi.estoque_api.dto.CategoriaCreateDTO;
import com.estoqueApi.estoque_api.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired; // Não é estritamente necessário com injeção por construtor, mas não causa erro
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional; // Importe Optional para usar findById
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // Injeção de dependência via construtor (melhor prática)
    // @Autowired // Comentado pois o Spring Boot já infere o @Autowired para construtores com uma única dependência
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaDTO criar(CategoriaCreateDTO dto) {
        Categoria cat = new Categoria();
        cat.setNome(dto.getNome());
        Categoria savedCat = categoriaRepository.save(cat); // Salve a entidade e capture o retorno

        CategoriaDTO retorno = new CategoriaDTO();
        retorno.setId(savedCat.getId()); // Use o ID da entidade salva
        retorno.setNome(savedCat.getNome());
        return retorno;
    }

    public List<CategoriaDTO> listarTodos() {
        return categoriaRepository.findAll()
                .stream()
                .map(cat -> {
                    CategoriaDTO dto = new CategoriaDTO();
                    dto.setId(cat.getId());
                    dto.setNome(cat.getNome());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // --- MÉTODOS ATUALIZAR E DELETAR ADICIONADOS/CORRIGIDOS ---

    public CategoriaDTO atualizar(Long id, CategoriaCreateDTO dto) {
        // Encontra a categoria pelo ID
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()) {
            Categoria categoriaExistente = categoriaOptional.get();
            categoriaExistente.setNome(dto.getNome()); // Atualiza o nome com base no DTO de entrada

            Categoria categoriaAtualizada = categoriaRepository.save(categoriaExistente); // Salva a categoria atualizada

            // Converte a entidade atualizada para DTO de retorno
            CategoriaDTO retornoDto = new CategoriaDTO();
            retornoDto.setId(categoriaAtualizada.getId());
            retornoDto.setNome(categoriaAtualizada.getNome());
            return retornoDto;
        } else {
            return null; // Retorna null se a categoria não for encontrada (o Controller lida com isso)
            // Ou você pode lançar uma exceção específica aqui e tratá-la no Controller com @ExceptionHandler
        }
    }

    public boolean deletar(Long id) {
        // Verifica se a categoria existe antes de tentar deletar
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true; // Retorna true se a remoção foi bem-sucedida
        }
        return false; // Retorna false se a categoria não foi encontrada
    }
}