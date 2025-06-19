package com.estoqueApi.estoque_api.service;

import com.estoqueApi.estoque_api.dto.VendaRequestDTO;
import com.estoqueApi.estoque_api.dto.VendaRequestDTO.ItemVendaDTO;
import com.estoqueApi.estoque_api.model.Produto;
import com.estoqueApi.estoque_api.model.Venda;
import com.estoqueApi.estoque_api.model.ItemVenda;
import com.estoqueApi.estoque_api.repository.ProdutoRepository;
import com.estoqueApi.estoque_api.repository.VendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// import java.time.LocalDateTime; // Remova esta importação, se não for mais usada em outro lugar
import java.util.HashSet;
import java.util.Set;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Venda processarVenda(VendaRequestDTO vendaRequest) {
        Venda venda = new Venda();
        // LINHA venda.setDataHora(LocalDateTime.now()); REMOVIDA
        venda.setTotal(0.0);

        Set<ItemVenda> itensVenda = new HashSet<>();
        double totalVenda = 0.0;

        for (ItemVendaDTO itemDto : vendaRequest.getItens()) {
            Produto produto = produtoRepository.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDto.getProdutoId()));

            if (itemDto.getQuantidadeVendida() <= 0) {
                throw new RuntimeException("Quantidade inválida para o produto: " + produto.getNome());
            }

            if (produto.getQuantidade() < itemDto.getQuantidadeVendida()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome() + ". Quantidade disponível: " + produto.getQuantidade());
            }

            produto.setQuantidade(produto.getQuantidade() - itemDto.getQuantidadeVendida());
            produtoRepository.save(produto);

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(itemDto.getQuantidadeVendida());
            itemVenda.setPrecoUnitario(itemDto.getPrecoUnitarioVenda());
            itemVenda.setVenda(venda);

            itensVenda.add(itemVenda);
            totalVenda += itemDto.getQuantidadeVendida() * itemDto.getPrecoUnitarioVenda();
        }

        venda.setItens(itensVenda);
        venda.setTotal(totalVenda);

        return vendaRepository.save(venda);
    }
}