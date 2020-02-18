package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.Dto.ProdutoDto;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public void cadastraProduto(ProdutoDto produtoDto) {
        Produto produto = new Produto();
        produto.setDescricao(produtoDto.getDescricao());
        produto.setValor(produtoDto.getValor());
        produtoRepository.save(produto);
    }
    //TODO - Verificar porque não consigo usat o construtor.
}
