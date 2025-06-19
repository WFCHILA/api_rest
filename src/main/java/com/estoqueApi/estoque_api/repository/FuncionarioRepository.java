package com.estoqueApi.estoque_api.repository;

import com.estoqueApi.estoque_api.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> { }