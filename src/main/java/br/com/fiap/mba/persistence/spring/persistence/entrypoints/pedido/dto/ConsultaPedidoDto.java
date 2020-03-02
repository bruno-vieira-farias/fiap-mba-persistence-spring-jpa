package br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido.dto;

import br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente.dto.ClienteDto;

import java.util.List;

public class ConsultaPedidoDto {
    private Integer id;

    private ClienteDto cliente;

    private List<ConsultaItemPedidoDto> itens;

    public ConsultaPedidoDto(Integer id, ClienteDto cliente, List<ConsultaItemPedidoDto> itens) {
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

    public List<ConsultaItemPedidoDto> getItens() {
        return itens;
    }

    public void setItens(List<ConsultaItemPedidoDto> itens) {
        this.itens = itens;
    }
}
