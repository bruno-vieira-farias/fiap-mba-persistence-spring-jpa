package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.Dto.ProdutoDto;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public void cadastraProduto(ProdutoDto produtoDto) {
        Produto produto = new Produto(produtoDto.getDescricao(), produtoDto.getValor());
        produtoRepository.save(produto);
    }
}
