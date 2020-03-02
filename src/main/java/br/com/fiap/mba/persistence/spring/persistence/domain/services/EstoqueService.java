package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Estoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.exception.ProdutoIndisponivelException;
import br.com.fiap.mba.persistence.spring.persistence.domain.exception.ProdutoJaPossuiEstoqueException;
import br.com.fiap.mba.persistence.spring.persistence.domain.exception.ProdutoNaoEncontradoException;
import br.com.fiap.mba.persistence.spring.persistence.domain.exception.ProdutoSemEstoqueException;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final ProdutoService produtoService;

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoService produtoService) {
        this.estoqueRepository = estoqueRepository;
        this.produtoService = produtoService;
    }

    @Transactional
    private Estoque buscaEstoqueDoProduto(Produto produto) throws ProdutoSemEstoqueException {
        Estoque estoque = estoqueRepository.findByProduto(produto);
        if(estoque == null)
            throw new ProdutoSemEstoqueException(produto.getCodigo());
        return estoque;
    }

    @Transactional
    public Estoque buscaEstoqueDoProduto(String codigoProduto) throws ProdutoNaoEncontradoException, ProdutoSemEstoqueException {
        Produto produto = produtoService.buscaProduto(codigoProduto);
        return buscaEstoqueDoProduto(produto);
    }

    @Transactional
    public void cadastraEstoque(String codigoProduto, Integer quantidade) throws ProdutoNaoEncontradoException, ProdutoJaPossuiEstoqueException {
        Produto produto = produtoService.buscaProduto(codigoProduto);
        certificaQueEstoquePodeSerCadastrado(produto);

        Estoque estoque = new Estoque(produto, quantidade);
        estoqueRepository.save(estoque);
    }

    public List<Estoque> buscaEstoque(){
        return estoqueRepository.findAll();
    }

    @Transactional
    public void alteraQuantidadeEstoque(String codigoProduto, Integer quantidade) throws ProdutoNaoEncontradoException, ProdutoSemEstoqueException {

        Estoque estoque = buscaEstoqueDoProduto(codigoProduto);
        estoque.setQuantidade(quantidade);

        estoqueRepository.save(estoque);
    }

    @Transactional
    public void removeItemEstoque(String codigoProduto) throws ProdutoNaoEncontradoException {
        Produto produto = produtoService.buscaProduto(codigoProduto);

        estoqueRepository.removeItemEstoqueByProduto(produto);
    }

    @Transactional
    public void consomeProdutoEstoque(Produto produto, Integer quantidadeConsumida) throws ProdutoIndisponivelException, ProdutoSemEstoqueException {
        Estoque estoque = buscaEstoqueDoProduto(produto);
        
        Integer quantidadeRestante = estoque.getQuantidade() - quantidadeConsumida;

        if(quantidadeRestante < 0)
            throw new ProdutoIndisponivelException(produto.getCodigo());

        estoque.setQuantidade(quantidadeRestante);
        estoqueRepository.save(estoque);
    }


    private void certificaQueEstoquePodeSerCadastrado(Produto produto) throws ProdutoJaPossuiEstoqueException {
        if (estoqueRepository.findByProduto(produto) != null) {
            throw new ProdutoJaPossuiEstoqueException(produto.getCodigo());
        }
    }
}