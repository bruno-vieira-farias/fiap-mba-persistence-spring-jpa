package br.com.fiap.mba.persistence.spring.persistence.entrypoints.estoque;

public class ItemEstoqueDto {
    private String codigoProduto;
    private Integer quantidade;

    public ItemEstoqueDto(String codigoProduto, Integer quantidade) {
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
