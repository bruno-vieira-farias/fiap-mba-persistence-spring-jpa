package br.com.fiap.mba.persistence.spring.persistence.domain.exception;

public class PedidoNaoEncontradoException extends Exception {
    public PedidoNaoEncontradoException(Integer id) {
        super("Pedido com o código " + id + "não encontrado.");
    }
}
