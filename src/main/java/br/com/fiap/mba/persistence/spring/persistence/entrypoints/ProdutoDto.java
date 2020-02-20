package br.com.fiap.mba.persistence.spring.persistence.entrypoints;

import java.math.BigDecimal;

public class ProdutoDto {
    private String codigo;
    private String descricao;
    private BigDecimal valor;
    private Integer quantidadeEstoque;

    public ProdutoDto() {
    }

    public ProdutoDto(String codigo, String descricao, BigDecimal valor, Integer quantidadeEstoque) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
