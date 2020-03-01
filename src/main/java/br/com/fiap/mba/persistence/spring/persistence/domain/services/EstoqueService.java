package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Estoque;
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
    public void cadastraEstoque(String codigoProduto, Integer quantidade) {
        Produto produto = produtoService.buscaProduto(codigoProduto);
        certificaQueEstoquePodeSerCadastrado(produto);

        Estoque estoque = new Estoque(produto, quantidade);
        estoqueRepository.save(estoque);
    }

    @Transactional
    public Estoque buscaEstoqueDoProduto(String codigoProduto) {
        Produto produto = produtoService.buscaProduto(codigoProduto);
        return estoqueRepository.findByProduto(produto);
    }

    @Transactional
    public void alteraQuantidadeEstoque(String codigoProduto, Integer quantidade) {
        Produto produto = produtoService.buscaProduto(codigoProduto);
        certificaQueEstoquePodeSerAlterado(produto);

        Estoque estoque = estoqueRepository.findByProduto(produto);
        estoque.setQuantidade(quantidade);

        estoqueRepository.save(estoque);
    }

    @Transactional
    public void removeItemEstoque(String codigoProduto) {
        Produto produto = produtoService.buscaProduto(codigoProduto);
        estoqueRepository.removeItemEstoqueByProduto(produto);
    }

    private void certificaQueEstoquePodeSerCadastrado(Produto produto){
        if (produto == null){
            throw new IllegalArgumentException("O cadastro do estoque não pode ser realizado porque o produto ainda não foi cadastrado.");
        }

        if (estoqueRepository.findByProduto(produto) != null){
            throw new IllegalArgumentException("O cadastro do estoque não pode ser realizado porque já existe este item no estoque.");
        }
    }

    private void certificaQueEstoquePodeSerAlterado(Produto produto){
        if (produto == null){
            throw new IllegalArgumentException("A alteracado no estoque não pode ser realizada porque o produto ainda não foi cadastrado.");
        }

        if (estoqueRepository.findByProduto(produto) == null){
            throw new IllegalArgumentException("O alteracao no estoque não pode ser realizado porque ainda não existe este item no estoque.");
        }
    }
}