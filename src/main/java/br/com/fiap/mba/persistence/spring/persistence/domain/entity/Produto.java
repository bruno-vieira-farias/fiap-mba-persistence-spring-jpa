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

    public Produto(String descricao, BigDecimal valor) {
    }
}
