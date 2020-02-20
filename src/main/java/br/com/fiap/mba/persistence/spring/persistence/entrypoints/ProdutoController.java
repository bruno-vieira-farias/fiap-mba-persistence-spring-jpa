package br.com.fiap.mba.persistence.spring.persistence.entrypoints;

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
        try {
            produtoService.cadastraProduto(
                    produtoDto.getCodigo(),
                    produtoDto.getDescricao(),
                    produtoDto.getValor(),
                    produtoDto.getQuantidadeEstoque()
            );
        } catch (IllegalAccessException e) {
            //Todo Analisar qual/como será o tipo de retorno e qual o código http mais apropriado.
            e.printStackTrace();
        }
    }
}
