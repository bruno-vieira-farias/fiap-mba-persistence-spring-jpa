package br.com.fiap.mba.persistence.spring.persistence.entrypoints.estoque;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.ItemEstoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.EstoqueService;
import org.springframework.web.bind.annotation.*;

@RestController("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping("adiciona")
    public void cadastraItemEstoque(@RequestBody ItemEstoqueDto itemEstoqueDto){
        estoqueService.cadastraItemEstoque(itemEstoqueDto.getCodigoProduto(), itemEstoqueDto.getQuantidade());
    }

    @GetMapping("consulta/{codigoProduto}")
    public ItemEstoqueDto consultaItemEstoque(@PathVariable String codigoProduto){
        ItemEstoque itemEstoque = estoqueService.buscaItemEstoque(codigoProduto);
        return new ItemEstoqueDto(
                itemEstoque.getProduto().getCodigo(),
                itemEstoque.getQuantidade());
    }

    @PutMapping("altera/{codigoProduto}")
    public void alteraQuantidadeItemEstoque(@RequestBody ItemEstoqueDto itemEstoqueDto){
        estoqueService.alteraQuantdadeItemEstoque(itemEstoqueDto.getCodigoProduto(), itemEstoqueDto.getQuantidade());
    }

    @DeleteMapping("{codigoProduto}")
    public void removeItemEstoque(@PathVariable String codigoProduto){
        estoqueService.removeItemEstoque(codigoProduto);
    }

}
