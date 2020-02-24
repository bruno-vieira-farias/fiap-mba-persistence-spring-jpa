package br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido;

import br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente.ClienteDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido.ItemPedidoDto;

import java.util.List;

public class PedidoDto {
    private Integer id;

    private ClienteDto cliente;

    private List<ItemPedidoDto> itens;

    public PedidoDto(Integer id, ClienteDto cliente, List<ItemPedidoDto> itens) {
        this.id = id;
        this.cliente = cliente;
        this.itens = itens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedidoDto> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDto> itens) {
        this.itens = itens;
    }
}
