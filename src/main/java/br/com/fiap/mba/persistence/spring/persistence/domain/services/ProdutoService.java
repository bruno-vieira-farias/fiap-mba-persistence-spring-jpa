package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.ItemEstoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.EstoqueRepository;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;

    public ProdutoService(ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository) {
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @Transactional
    //TODO - Verificar porque não consigo usat o construtor.
    public void cadastraProduto(String codigo, String descricao, BigDecimal valor, Integer quantidadeEstoque) throws IllegalAccessException {
        certificaQueProdutoPodeSerCadastrado(codigo);

        Produto produto = new Produto(codigo, descricao, valor);
        produtoRepository.save(produto);

        ItemEstoque estoque =  new ItemEstoque(produto,quantidadeEstoque);
        estoqueRepository.save(estoque);
    }

    private void certificaQueProdutoPodeSerCadastrado(String codigo) throws IllegalAccessException {
        if (produtoRepository.findByCodigo(codigo) != null) {
            throw new IllegalAccessException("O produto de codigo" + codigo + "já esta cadastrado.");
        }
    }
}