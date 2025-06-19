package com.estoqueApi.estoque_api.repository;

import com.estoqueApi.estoque_api.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> { }