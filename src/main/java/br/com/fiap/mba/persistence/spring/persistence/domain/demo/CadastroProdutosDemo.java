package br.com.fiap.mba.persistence.spring.persistence.domain.demo;

import br.com.fiap.mba.persistence.spring.persistence.domain.produto.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.produto.ProdutoRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class CadastroProdutosDemo {

    private ProdutoRepository produtoRepository;

    public CadastroProdutosDemo(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void cadastraProdutosDemo(){
        List<Produto> produtos = Arrays.asList(
                new Produto("123","Arroz Tio João", BigDecimal.valueOf(14)),
                new Produto("123","Feijão Carioca", BigDecimal.valueOf(7.5)),
                new Produto("123","Açucar Doce Vida", BigDecimal.valueOf(1.99)),
                new Produto("123","Farinha de Trigo", BigDecimal.valueOf(2.4)),
                new Produto("123","Fermento", BigDecimal.valueOf(5.25)),
                new Produto("123","Macarrão do Bão", BigDecimal.valueOf(1.8)),
                new Produto("123","Coca Cola 2L", BigDecimal.valueOf(5.99)),
                new Produto("123","Detergente Limpex", BigDecimal.valueOf(1.45))
        );

        produtoRepository.saveAll(produtos);
    }
}
