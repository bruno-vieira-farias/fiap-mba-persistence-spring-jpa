package br.com.fiap.mba.persistence.spring.persistence.domain.services.pedido;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.ItemPedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Pedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.PedidoRepository;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.EstoqueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoFactory pedidoFactory;
    private final EstoqueService estoqueService;

    public PedidoService(PedidoRepository pedidoRepository, PedidoFactory pedidoFactory, EstoqueService estoqueService) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoFactory = pedidoFactory;
        this.estoqueService = estoqueService;
    }

    @Transactional
    public void emitePedido(EspecificacaoPedido especificacaoPedido) {
        Pedido pedido = pedidoFactory.criaPedido(especificacaoPedido);
        pedido.getItens().forEach(this::consomeProdutoEstoque);

        pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido consultaPedido(Integer id) {
        return pedidoRepository.findById(id).get();
    }

    private void consomeProdutoEstoque(ItemPedido itemPedido){
        estoqueService.consomeProdutoEstoque(itemPedido.getProduto(),itemPedido.getQuantidade());
    }
}
