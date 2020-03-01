package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.ProdutoRepository;
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
    public void cadastraProduto(String codigo, String descricao, BigDecimal valor) throws IllegalAccessException {
        certificaQueProdutoPodeSerCadastrado(codigo);

        Produto produto = new Produto(codigo, descricao, valor);
        produtoRepository.save(produto);
    }

    @Transactional
    public List<Produto> buscaProdutos() {
        return produtoRepository.findAll();
    }

    @Transactional
    public Produto buscaProduto(String codigo){
        return produtoRepository.findByCodigo(codigo);
    }

    @Transactional
    public void apagaProduto(String codigo) {
        //Todo - Tratar para validar se o produto existe.
        Produto produto = produtoRepository.findByCodigo(codigo);
        produtoRepository.delete(produto);
    }

    @Transactional
    public void alteraProduto(String codigo, String descricao, BigDecimal valor){
        Produto produto = buscaProduto(codigo);

        if (produto == null) {
            throw new IllegalArgumentException("O Produto que deseja alterar ainda não existe na base de dados.");
        }

        produto.setDescricao(descricao);
        produto.setValor(valor);

        produtoRepository.save(produto);
    }

    private void certificaQueProdutoPodeSerCadastrado(String codigo) throws IllegalAccessException {
        if (produtoRepository.findByCodigo(codigo) != null) {
            throw new IllegalAccessException("O produto de código" + codigo + "já esta cadastrado.");
        }
    }
}