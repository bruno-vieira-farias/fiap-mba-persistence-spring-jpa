package br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Pedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.pedido.EspecificacaoItemPedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.pedido.EspecificacaoPedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.pedido.PedidoService;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente.ClienteDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente.EnderecoDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido.dto.ConsultaItemPedidoDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido.dto.ConsultaPedidoDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido.dto.EmissaoPedidoDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.produto.ProdutoDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping()
    private void emitePedido(@RequestBody EmissaoPedidoDto emissaoPedidoDto) {
        EspecificacaoPedido especificacaoPedido = new EspecificacaoPedido(
                emissaoPedidoDto.getCpfCliente(),
                emissaoPedidoDto.getItensPedido().stream()
                        .map(item -> new EspecificacaoItemPedido(
                                        item.getCodigoProduto(),
                                        item.getQuantidade()
                                )
                        ).collect(Collectors.toList())
        );

        pedidoService.emitePedido(especificacaoPedido);
    }

    @GetMapping("/{id}")
    public ConsultaPedidoDto consultaPedido(@PathVariable Integer id) {
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

        List<ConsultaItemPedidoDto> itensPedidoDto = pedido.getItens().stream().map(item ->
                new ConsultaItemPedidoDto(
                        new ProdutoDto(
                                item.getProduto().getCodigo(),
                                item.getProduto().getDescricao(),
                                item.getProduto().getValor()
                        ),
                        item.getQuantidade()
                )
        ).collect(Collectors.toList());

        return new ConsultaPedidoDto(
                pedido.getId(),
                clienteDto,
                itensPedidoDto
        );
    }
}