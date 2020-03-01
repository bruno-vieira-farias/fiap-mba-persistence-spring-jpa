package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Pedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoFactory pedidoFactory;

    public PedidoService(PedidoRepository pedidoRepository, PedidoFactory pedidoFactory) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoFactory = pedidoFactory;
    }

    @Transactional
    public void emitePedido(EspecificacaoPedido especificacaoPedido) {
        Pedido pedido = pedidoFactory.criaPedido(especificacaoPedido);
//        Todo -> verifica se tem a quantidade em estoque e dar baixa.
        pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido consultaPedido(Integer id) {
        return pedidoRepository.findById(id).get();
    }
}
