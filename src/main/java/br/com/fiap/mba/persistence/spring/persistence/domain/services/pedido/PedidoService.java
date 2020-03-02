package br.com.fiap.mba.persistence.spring.persistence.domain.services.pedido;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.ItemPedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Pedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.exception.PedidoInvalidoException;
import br.com.fiap.mba.persistence.spring.persistence.domain.exception.PedidoNaoEncontradoException;
import br.com.fiap.mba.persistence.spring.persistence.domain.exception.ProdutoIndisponivelException;
import br.com.fiap.mba.persistence.spring.persistence.domain.exception.ProdutoSemEstoqueException;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.PedidoRepository;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.EstoqueService;
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
    public void emitePedido(EspecificacaoPedido especificacaoPedido) throws PedidoInvalidoException {
        try {
            Pedido pedido = pedidoFactory.criaPedido(especificacaoPedido);

            for (ItemPedido itemPedido : pedido.getItens()) {
                    consomeProdutoEstoque(itemPedido);
            }

            pedidoRepository.save(pedido);
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
