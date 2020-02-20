package br.com.fiap.mba.persistence.spring.persistence.entrypoints;

import java.math.BigDecimal;

public class ConsultaProdutoDto {
    private String codigo;
    private String descricao;
    private BigDecimal valor;

    public ConsultaProdutoDto() {
    }

    public ConsultaProdutoDto(String codigo, String descricao, BigDecimal valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
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

}
