package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.ItemEstoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.EstoqueRepository;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;

    public ProdutoService(ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository) {
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @Transactional
    public void cadastraProduto(String codigo, String descricao, BigDecimal valor) throws IllegalAccessException {
        certificaQueProdutoPodeSerCadastrado(codigo);

        Produto produto = new Produto(codigo, descricao, valor);
        produtoRepository.save(produto);

//        ItemEstoque estoque =  new ItemEstoque(produto,quantidadeEstoque);
//        estoqueRepository.save(estoque);
    }

    public List<Produto> buscaTodosProdutos(){
        return produtoRepository.findAll();
    }

    private void certificaQueProdutoPodeSerCadastrado(String codigo) throws IllegalAccessException {
        if (produtoRepository.findByCodigo(codigo) != null) {
            throw new IllegalAccessException("O produto de código" + codigo + "já esta cadastrado.");
        }
    }

    //Todo - Tratar para validar se o produto existe.
    public void apagaProduto(String codigo) {
        Produto produto = produtoRepository.findByCodigo(codigo);
        produtoRepository.delete(produto);
    }
}