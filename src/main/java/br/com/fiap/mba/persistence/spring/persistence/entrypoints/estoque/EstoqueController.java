package br.com.fiap.mba.persistence.spring.persistence.entrypoints.estoque;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Estoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.EstoqueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/estoque-produto")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping()
    public void cadastraProdutoEstoque(@RequestBody EstoqueDto estoqueDto) {
        estoqueService.cadastraEstoque(estoqueDto.getCodigoProduto(), estoqueDto.getQuantidade());
    }

    @GetMapping("{codigoProduto}")
    public EstoqueDto buscaEstoqueDoProduto(@PathVariable String codigoProduto) {
        Estoque estoque = estoqueService.buscaEstoqueDoProduto(codigoProduto);

        if (estoque == null){
            return null;
        }

        return new EstoqueDto(
                estoque.getProduto().getCodigo(),
                estoque.getQuantidade());
    }

    @PutMapping()
    public void alteraQuantidadeProdutoEstoque(@RequestBody EstoqueDto estoqueDto) {
        estoqueService.alteraQuantidadeEstoque(estoqueDto.getCodigoProduto(), estoqueDto.getQuantidade());
    }

    @DeleteMapping("{codigoProduto}")
    public void removeProdutoEstoque(@PathVariable String codigoProduto) {
        estoqueService.removeItemEstoque(codigoProduto);
    }

    @GetMapping()
    public List<EstoqueDto> buscaEstoqueDoProduto() {
        List<Estoque> listaEstoque = estoqueService.buscaEstoque();

        if (listaEstoque.isEmpty()){
            return null;
        }

        return listaEstoque.stream().map(estoque ->
                        new EstoqueDto(
                                estoque.getProduto().getCodigo(),
                                estoque.getQuantidade())
                ).collect(Collectors.toList());
    }
}