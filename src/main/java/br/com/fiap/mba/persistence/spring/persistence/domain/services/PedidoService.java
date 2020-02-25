package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Cliente;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.ItemPedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Pedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.ClienteRepository;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.PedidoRepository;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Pedido consultaPedido(Integer id){
        return pedidoRepository.findById(id).get();
    }

    @Transactional
    //Todo - Criar uma factory para o pedido.
    public void emitePedido(EspecificacaoPedido especificacaoPedido){
        Cliente cliente = clienteRepository.findByCpf(especificacaoPedido.getCpfCliente());
        Pedido pedido = new Pedido(
                cliente,
                especificacaoPedido.getEspecificacaoItemPedido().stream()
                        .map(item -> new ItemPedido(produtoRepository.findByCodigo(item.getCodigoProduto()), item.getQuantidade()))
                        .collect(Collectors.toList())
        );
        pedidoRepository.save(pedido);
    }
}
