package com.estoqueApi.estoque_api.repository;

import com.estoqueApi.estoque_api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}