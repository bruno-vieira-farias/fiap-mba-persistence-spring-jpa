package br.com.fiap.mba.persistence.spring.persistence.domain.entity;

import javax.persistence.*;
import java.util.List;


public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    private List<ItemPedido> itens;

}
