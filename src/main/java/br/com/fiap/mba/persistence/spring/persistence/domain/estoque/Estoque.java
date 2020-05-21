package br.com.fiap.mba.persistence.spring.persistence.domain.estoque;

import br.com.fiap.mba.persistence.spring.persistence.domain.produto.Produto;

import javax.persistence.*;

/**
 *  Representa cada produto no estoque.
 */
@Entity
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne()
    private Produto produto;
    private Integer quantidade;

    public Estoque() {
    }

    public Estoque(Produto produto, Integer quantidade) {
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
        if (quantidade < 0) {
            throw new IllegalArgumentException("O estoque de um produto não pode ser negativo.");
        }

        this.quantidade = quantidade;
    }
}
