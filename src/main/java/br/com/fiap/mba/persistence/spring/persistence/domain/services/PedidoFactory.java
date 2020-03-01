package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Cliente;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.ItemPedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Pedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.ClienteRepository;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PedidoFactory {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoFactory(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public Pedido criaPedido(EspecificacaoPedido especificacaoPedido){
        Cliente cliente = buscaClientePorCpf(especificacaoPedido.getCpfCliente());

        return new Pedido(
                cliente,
                especificacaoPedido.getEspecificacaoItemPedido().stream()
                        .map(item ->
                                new ItemPedido(buscaProdutoPorCodigo(item.getCodigoProduto()), certificaQueQuantidadeEhValida(item.getQuantidade()))
                        )
                        .collect(Collectors.toList())
        );
    }

    private Cliente buscaClientePorCpf(String cpf){
        Cliente cliente = clienteRepository.findByCpf(cpf);

        if (cliente == null){
            throw new IllegalArgumentException("Não é possível criar o pedido porque o cliente não esta cadastrado.");
        }

        return cliente;
    }

    private Produto buscaProdutoPorCodigo(String codigo){
        Produto produto = produtoRepository.findByCodigo(codigo);
        if (produto == null){
            throw new IllegalArgumentException("Não é possível criar o pedido porque existe(m) produto(s) não cadastrado(s).");
        }
        return produto;
    }

    private Integer certificaQueQuantidadeEhValida(Integer quantidade){
        if (quantidade < 1) {
            throw new IllegalArgumentException("Não é possível criar o pedido porque existe(m) produto(s) com quantidade menor que o mínimo de 1 .");
        }
        return quantidade;
    }
}
