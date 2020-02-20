package br.com.fiap.mba.persistence.spring.persistence.entrypoints;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/cadastra")
    public void cadastraProduto(@RequestBody CadastroProdutoDto cadastroProdutoDto) {
        try {
            produtoService.cadastraProduto(
                    cadastroProdutoDto.getCodigo(),
                    cadastroProdutoDto.getDescricao(),
                    cadastroProdutoDto.getValor()
            );
        } catch (IllegalAccessException e) {
            //Todo Analisar qual/como será o tipo de retorno e qual o código http mais apropriado.
            e.printStackTrace();
        }
    }

    @GetMapping("/busca-todos")
    public List<ConsultaProdutoDto> buscaTodosProdutos(){
        return produtoService.buscaTodosProdutos().stream()
                .map(produto ->
                    new ConsultaProdutoDto(
                            produto.getCodigo(),
                            produto.getDescricao(),
                            produto.getValor())
                ).collect(Collectors.toList());
    }

    @DeleteMapping("{codigo}")
    public void apagaProduto(@PathVariable String codigo){
        produtoService.apagaProduto(codigo);
    }
}
