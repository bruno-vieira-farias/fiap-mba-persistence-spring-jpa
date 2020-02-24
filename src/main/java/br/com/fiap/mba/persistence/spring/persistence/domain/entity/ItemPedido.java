package br.com.fiap.mba.persistence.spring.persistence.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemPedido {
    @Id
    private Integer id;

    @ManyToOne
    private Produto item;

    private Integer quantidade;

}
