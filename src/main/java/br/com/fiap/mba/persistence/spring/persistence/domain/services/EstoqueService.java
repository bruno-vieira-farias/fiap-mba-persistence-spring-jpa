package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.ItemEstoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final ProdutoService produtoService;

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoService produtoService) {
        this.estoqueRepository = estoqueRepository;
        this.produtoService = produtoService;
    }

    @Transactional
    public void cadastraItemEstoque(String codigoProduto, Integer quantidade) {
        Produto produto = produtoService.buscaProduto(codigoProduto);
        ItemEstoque item = new ItemEstoque(produto,quantidade);
        estoqueRepository.save(item);
    }

    @Transactional
    public ItemEstoque buscaItemEstoque(String codigoProduto) {
        Produto produto = produtoService.buscaProduto(codigoProduto);
        return estoqueRepository.findByProduto(produto);
    }

    @Transactional
    public void
    alteraQuantdadeItemEstoque(String codigoProduto, Integer quantidade) {
        Produto produto = produtoService.buscaProduto(codigoProduto);
        ItemEstoque item = estoqueRepository.findByProduto(produto);
        item.setQuantidade(quantidade);
        estoqueRepository.save(item);
    }

    @Transactional
    public void removeItemEstoque(String codigoProduto) {
        Produto produto = produtoService.buscaProduto(codigoProduto);
        estoqueRepository.removeItemEstoqueByProduto(produto);
    }
}
