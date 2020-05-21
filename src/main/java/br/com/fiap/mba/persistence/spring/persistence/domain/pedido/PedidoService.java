package br.com.fiap.mba.persistence.spring.persistence.domain.pedido;

import br.com.fiap.mba.persistence.spring.persistence.domain.pedido.*;
import br.com.fiap.mba.persistence.spring.persistence.domain.produto.ProdutoIndisponivelException;
import br.com.fiap.mba.persistence.spring.persistence.domain.estoque.ProdutoSemEstoqueException;
import br.com.fiap.mba.persistence.spring.persistence.domain.estoque.EstoqueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Pedido emitePedido(EspecificacaoPedido especificacaoPedido) throws PedidoInvalidoException {
        try {
            Pedido pedido = pedidoFactory.criaPedido(especificacaoPedido);

            for (ItemPedido itemPedido : pedido.getItens()) {
                    consomeProdutoEstoque(itemPedido);
            }

            return pedidoRepository.save(pedido);
        } catch (ProdutoIndisponivelException | ProdutoSemEstoqueException e) {
            throw new PedidoInvalidoException(e.getMessage());
        }
    }

    @Transactional
    public Pedido consultaPedido(Integer id) throws PedidoNaoEncontradoException {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (!pedido.isPresent())
            throw new PedidoNaoEncontradoException(id);

        return pedido.get();
    }

    private void consomeProdutoEstoque(ItemPedido itemPedido) throws ProdutoIndisponivelException, ProdutoSemEstoqueException {
        estoqueService.consomeProdutoEstoque(itemPedido.getProduto(),itemPedido.getQuantidade());
    }
}
