package br.com.fiap.mba.persistence.spring.persistence.domain.Dto;

import java.math.BigDecimal;

public class ProdutoDto {
    private String descricao;
    private BigDecimal valor;

    public ProdutoDto() {
    }

    public ProdutoDto(String descricao, BigDecimal valor) {
        this.descricao = descricao;
        this.valor = valor;
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
}
