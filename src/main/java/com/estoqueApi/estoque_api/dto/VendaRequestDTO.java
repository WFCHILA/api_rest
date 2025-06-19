package com.estoqueApi.estoque_api.dto;

import java.util.List;

public class VendaRequestDTO {
    private List<ItemVendaDTO> itens;

    public VendaRequestDTO() {}

    public List<ItemVendaDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaDTO> itens) {
        this.itens = itens;
    }

    public static class ItemVendaDTO {
        private Long produtoId;
        private Integer quantidadeVendida;
        private Double precoUnitarioVenda;

        public ItemVendaDTO() {}

        public Long getProdutoId() {
            return produtoId;
        }

        public void setProdutoId(Long produtoId) {
            this.produtoId = produtoId;
        }

        public Integer getQuantidadeVendida() {
            return quantidadeVendida;
        }

        public void setQuantidadeVendida(Integer quantidadeVendida) {
            this.quantidadeVendida = quantidadeVendida;
        }

        public Double getPrecoUnitarioVenda() {
            return precoUnitarioVenda;
        }

        public void setPrecoUnitarioVenda(Double precoUnitarioVenda) {
            this.precoUnitarioVenda = precoUnitarioVenda;
        }
    }
}