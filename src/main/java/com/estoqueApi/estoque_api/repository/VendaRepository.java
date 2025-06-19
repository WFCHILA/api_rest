package com.estoqueApi.estoque_api.repository; // PACOTE CORRIGIDO!

import com.estoqueApi.estoque_api.model.Venda; // Já deveria encontrar Venda agora
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}