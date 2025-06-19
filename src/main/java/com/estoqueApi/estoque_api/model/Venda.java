package com.estoqueApi.estoque_api.model;

import jakarta.persistence.*;
// import java.time.LocalDateTime; // Remova esta importação
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campo dataHora REMOVIDO

    @Column(name = "total_venda", nullable = false)
    private Double total;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemVenda> itens = new HashSet<>();

    public Venda() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Métodos getDataHora() e setDataHora() REMOVIDOS

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Set<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(Set<ItemVenda> itens) {
        this.itens = itens;
    }

    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
        item.setVenda(this);
    }
}