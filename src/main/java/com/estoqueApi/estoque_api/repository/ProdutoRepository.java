package com.estoqueApi.estoque_api.repository; // PACOTE CORRIGIDO!

import com.estoqueApi.estoque_api.model.Produto; // Já deveria encontrar Produto agora
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}