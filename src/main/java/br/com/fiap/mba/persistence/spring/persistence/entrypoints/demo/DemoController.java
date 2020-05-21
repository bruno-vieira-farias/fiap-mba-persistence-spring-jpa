package br.com.fiap.mba.persistence.spring.persistence.entrypoints.demo;

import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.Cliente;
import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.ClienteJaExisteException;
import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.ClienteService;
import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.EspecificacaoCliente;
import br.com.fiap.mba.persistence.spring.persistence.domain.estoque.Estoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.estoque.EstoqueService;
import br.com.fiap.mba.persistence.spring.persistence.domain.pedido.*;
import br.com.fiap.mba.persistence.spring.persistence.domain.produto.*;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.demo.dao.ResultadoDemonstracaoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;

@RestController
@RequestMapping("/demonstracao")
public class DemoController {
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final EstoqueService estoqueService;
    private final PedidoService pedidoService;

    public DemoController(ClienteService clienteService, ProdutoService produtoService, EstoqueService estoqueService, PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.estoqueService = estoqueService;
        this.pedidoService = pedidoService;
    }

    @GetMapping()
    public ResultadoDemonstracaoDto handle() {
        try {
            Cliente cliente = cadastraClienteDemonstracao();
            Produto produto = cadastraProdutoDemostracao();
            Estoque estoque = cadastraEstoqueDemonstracao(produto);
            Pedido pedido = emitePedidoDemonstracao(cliente, produto);

            ResultadoDemonstracaoDto resultado = new ResultadoDemonstracaoDto(cliente, produto, estoque, pedido);

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar realizar a demonstração.");
        }

        return null;
    }

    private Pedido emitePedidoDemonstracao(Cliente cliente, Produto produto) throws PedidoInvalidoException {
        return pedidoService.emitePedido(new EspecificacaoPedido(
                cliente.getCpf(),
                Collections.singletonList(new EspecificacaoItemPedido(produto.getCodigo(), 2))));
    }

    private Estoque cadastraEstoqueDemonstracao(Produto produto) throws ProdutoNaoEncontradoException, ProdutoJaPossuiEstoqueException {
        return estoqueService.cadastraEstoque(produto.getCodigo(), 100);
    }

    private Produto cadastraProdutoDemostracao() throws ProdutoJaCadastradoException {
        return produtoService.cadastraProduto(
                "5485",
                "Kindle 10a. geração com iluminação embutida – Cor Preta",
                BigDecimal.valueOf(349));
    }

    private Cliente cadastraClienteDemonstracao() throws ClienteJaExisteException {
        return clienteService.cadastraCliente(
                new EspecificacaoCliente(
                        "Raimundo da Silva", "12345678910", "Rua do sucesso", 10, null, "032212222", "São Paulo", "SP"));
    }

}
