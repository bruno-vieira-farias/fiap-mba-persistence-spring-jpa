package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.exception.ProdutoJaCadastradoException;
import br.com.fiap.mba.persistence.spring.persistence.domain.exception.ProdutoNaoEncontradoException;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.ProdutoRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public void cadastraProduto(String codigo, String descricao, BigDecimal valor) throws ProdutoJaCadastradoException {
        certificaQueProdutoPodeSerCadastrado(codigo);

        Produto produto = new Produto(codigo, descricao, valor);
        produtoRepository.save(produto);
    }

    @Transactional
    public List<Produto> buscaProdutos() {
        return produtoRepository.findAll();
    }

    @Transactional
    public Produto buscaProduto(String codigo) throws ProdutoNaoEncontradoException {
        Produto produto = produtoRepository.findByCodigo(codigo);

        if (produto == null){
            throw new ProdutoNaoEncontradoException(codigo);
        }

        return produto;
    }

    @Transactional
    public void apagaProduto(String codigo) throws ProdutoNaoEncontradoException {
        Produto produto = buscaProduto(codigo);
        produtoRepository.delete(produto);
    }

    @Transactional
    public void alteraProduto(String codigo, String descricao, BigDecimal valor) throws ProdutoNaoEncontradoException{
        Produto produto = buscaProduto(codigo);

        produto.setDescricao(descricao);
        produto.setValor(valor);

        produtoRepository.save(produto);
    }

    private void certificaQueProdutoPodeSerCadastrado(String codigo) throws ProdutoJaCadastradoException {
        if (produtoRepository.findByCodigo(codigo) != null) {
            throw new ProdutoJaCadastradoException(codigo);
        }
    }
}