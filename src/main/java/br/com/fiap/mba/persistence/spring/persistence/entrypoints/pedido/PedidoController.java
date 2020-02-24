package br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Pedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.PedidoService;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente.ClienteDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente.EnderecoDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.produto.ProdutoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {

        this.pedidoService = pedidoService;
    }

    @GetMapping("consulta/{id}")
    public PedidoDto consultaPedido(Integer id){
        Pedido pedido = pedidoService.consultaPedido(id);
        //Todo - Colocar este linguicao em um helper. Object mapper, convert...
        ClienteDto clienteDto = new ClienteDto(
                pedido.getCliente().getNome(),
                pedido.getCliente().getCpf(),
                new EnderecoDto(
                        pedido.getCliente().getEndereco().getLogradouro(),
                        pedido.getCliente().getEndereco().getNumero(),
                        pedido.getCliente().getEndereco().getComplemento(),
                        pedido.getCliente().getEndereco().getCep(),
                        pedido.getCliente().getEndereco().getCidade(),
                        pedido.getCliente().getEndereco().getEstado()
                )
        );

        List<ItemPedidoDto> itensPedidoDto = pedido.getItens().stream().map(item ->
                new ItemPedidoDto(
                        new ProdutoDto(
                                item.getProduto().getCodigo(),
                                item.getProduto().getDescricao(),
                                item.getProduto().getValor()
                        ),
                        item.getQuantidade()
                )
        ).collect(Collectors.toList());

        return new PedidoDto(
                pedido.getId(),
                clienteDto,
                itensPedidoDto
        );

    }
}
