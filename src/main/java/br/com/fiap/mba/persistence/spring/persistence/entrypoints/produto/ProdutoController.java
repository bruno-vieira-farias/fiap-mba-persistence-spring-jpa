package br.com.fiap.mba.persistence.spring.persistence.entrypoints.produto;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping()
    public void cadastraProduto(@RequestBody ProdutoDto produtoDto) {
        try {
            produtoService.cadastraProduto(
                    produtoDto.getCodigo(),
                    produtoDto.getDescricao(),
                    produtoDto.getValor()
            );
        } catch (IllegalAccessException e) {
            //Todo Analisar qual/como será o tipo de retorno e qual o código http mais apropriado.
            e.printStackTrace();
        }
    }

    @GetMapping()
    public List<ProdutoDto> buscaProdutos() {
        return produtoService.buscaProdutos().stream()
                .map(produto ->
                        new ProdutoDto(
                                produto.getCodigo(),
                                produto.getDescricao(),
                                produto.getValor())
                ).collect(Collectors.toList());
    }

    @GetMapping("{codigo}")
    public ProdutoDto buscaProduto(@PathVariable String codigo) {
        Produto produto = produtoService.buscaProduto(codigo);

        if (produto == null){
            return null;
        }

        return new ProdutoDto(
                produto.getCodigo(),
                produto.getDescricao(),
                produto.getValor());
    }

    @PutMapping()
    public void alteraProduto(@RequestBody ProdutoDto produtoDto) {
        produtoService.alteraProduto(produtoDto.getCodigo(), produtoDto.getDescricao(), produtoDto.getValor());
    }

    @DeleteMapping("{codigo}")
    public void apagaProduto(@PathVariable String codigo) {
        produtoService.apagaProduto(codigo);
    }
}