package br.com.fiap.mba.persistence.spring.persistence.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String descricao;
    private BigDecimal valor;

    public Produto() {
    }

    public Produto(Integer id, String descricao, BigDecimal valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Produto(String descricao, BigDecimal valor) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
