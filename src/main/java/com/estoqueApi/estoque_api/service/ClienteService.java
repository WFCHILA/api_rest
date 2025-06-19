package com.estoqueApi.estoque_api.service;

import com.estoqueApi.estoque_api.repository.ClienteRepository;
import com.estoqueApi.estoque_api.dto.ClienteDTO;
import com.estoqueApi.estoque_api.dto.ClienteCreateDTO;
import com.estoqueApi.estoque_api.model.Cliente;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO criar(ClienteCreateDTO dto) {
        Cliente c = new Cliente();
        c.setNome(dto.getNome());
        c.setEmail(dto.getEmail());
        c.setTelefone(dto.getTelefone());
        Cliente savedCliente = clienteRepository.save(c);

        ClienteDTO retorno = new ClienteDTO();
        retorno.setId(savedCliente.getId());
        retorno.setNome(savedCliente.getNome());
        retorno.setEmail(savedCliente.getEmail());
        retorno.setTelefone(savedCliente.getTelefone());
        return retorno;
    }

    public List<ClienteDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(c -> {
                    ClienteDTO dto = new ClienteDTO();
                    dto.setId(c.getId());
                    dto.setNome(c.getNome());
                    dto.setEmail(c.getEmail());
                    dto.setTelefone(c.getTelefone());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public ClienteDTO atualizar(Long id, ClienteCreateDTO dto) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isPresent()) {
            Cliente clienteExistente = clienteOptional.get();
            clienteExistente.setNome(dto.getNome());
            clienteExistente.setEmail(dto.getEmail());
            clienteExistente.setTelefone(dto.getTelefone());

            Cliente updatedCliente = clienteRepository.save(clienteExistente);

            ClienteDTO retorno = new ClienteDTO();
            retorno.setId(updatedCliente.getId());
            retorno.setNome(updatedCliente.getNome());
            retorno.setEmail(updatedCliente.getEmail());
            retorno.setTelefone(updatedCliente.getTelefone());
            return retorno;
        } else {
            return null;
        }
    }

    public boolean deletar(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}