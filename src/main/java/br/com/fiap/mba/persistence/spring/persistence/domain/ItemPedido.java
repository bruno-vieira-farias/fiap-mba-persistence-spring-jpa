package br.com.fiap.mba.persistence.spring.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemPedido {
    @Id
    private Integer id;

    @ManyToOne
    private Produto produto;

    private Integer quantidade;

}
