package com.estoqueApi.estoque_api.repository;

import com.estoqueApi.estoque_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Interface extends JpaRepository<Produto, Long> {
}
