package br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido.dto;

import java.util.List;

public class EmissaoPedidoDto {
    private String cpfCliente;
    private List<EmissaoItemPedidoDto> emissaoItemPedidoDtos;

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public List<EmissaoItemPedidoDto> getEmissaoItemPedidoDtos() {
        return emissaoItemPedidoDtos;
    }

    public void setEmissaoItemPedidoDtos(List<EmissaoItemPedidoDto> emissaoItemPedidoDtos) {
        this.emissaoItemPedidoDtos = emissaoItemPedidoDtos;
    }
}
