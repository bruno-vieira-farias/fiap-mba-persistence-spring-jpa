package br.com.fiap.mba.persistence.spring.persistence.entrypoints.estoque;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Estoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.EstoqueService;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/estoque-produto")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping()
    public void cadastraItemEstoque(@RequestBody ItemEstoqueDto itemEstoqueDto) {
        estoqueService.cadastraEstoque(itemEstoqueDto.getCodigoProduto(), itemEstoqueDto.getQuantidade());
    }

    @GetMapping("{codigoProduto}")
    public ItemEstoqueDto buscaEstoqueDoProduto(@PathVariable String codigoProduto) {
        Estoque estoque = estoqueService.buscaEstoqueDoProduto(codigoProduto);

        if (estoque == null){
            return null;
        }

        return new ItemEstoqueDto(
                estoque.getProduto().getCodigo(),
                estoque.getQuantidade());
    }

    @PutMapping()
    public void alteraQuantidadeItemEstoque(@RequestBody ItemEstoqueDto itemEstoqueDto) {
        estoqueService.alteraQuantidadeEstoque(itemEstoqueDto.getCodigoProduto(), itemEstoqueDto.getQuantidade());
    }

    @DeleteMapping("{codigoProduto}")
    public void removeItemEstoque(@PathVariable String codigoProduto) {
        estoqueService.removeItemEstoque(codigoProduto);
    }
}