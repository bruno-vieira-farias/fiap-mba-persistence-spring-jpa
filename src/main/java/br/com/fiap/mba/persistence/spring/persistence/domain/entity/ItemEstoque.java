package br.com.fiap.mba.persistence.spring.persistence.domain.entity;

import javax.persistence.*;

/**
 *  Representa cada produto no estoque.
 */
@Entity
public class ItemEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Produto produto;
    private Integer quantidade;

    public ItemEstoque() {
    }

    public ItemEstoque(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
