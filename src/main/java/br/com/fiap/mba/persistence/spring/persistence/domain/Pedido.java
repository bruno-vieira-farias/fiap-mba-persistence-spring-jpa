package br.com.fiap.mba.persistence.spring.persistence.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private List<ItemPedido> itens;





}
