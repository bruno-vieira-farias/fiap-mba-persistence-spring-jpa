package br.com.fiap.mba.persistence.spring.persistence.entrypoints;

import br.com.fiap.mba.persistence.spring.persistence.domain.Dto.ProdutoDto;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.ProdutoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/cadastra-produto")
    public void cadastraProduto(@RequestBody ProdutoDto produtoDto) {
        produtoService.cadastraProduto(produtoDto);
    }
}
