package com.estoqueApi.estoque_api.repository;

import com.estoqueApi.estoque_api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}