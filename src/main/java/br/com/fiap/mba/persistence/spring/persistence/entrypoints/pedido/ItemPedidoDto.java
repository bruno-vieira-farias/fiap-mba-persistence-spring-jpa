package br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido;

import br.com.fiap.mba.persistence.spring.persistence.entrypoints.produto.ProdutoDto;

public class ItemPedidoDto {
    private ProdutoDto item;
    private Integer quantidade;

    public ItemPedidoDto(ProdutoDto item, Integer quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public ProdutoDto getItem() {
        return item;
    }

    public void setItem(ProdutoDto item) {
        this.item = item;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
