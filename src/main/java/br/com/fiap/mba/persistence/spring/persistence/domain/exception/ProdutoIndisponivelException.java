package br.com.fiap.mba.persistence.spring.persistence.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProdutoIndisponivelException extends Exception {
    public ProdutoIndisponivelException(String codigo) {
        super("O Produto de código " + codigo + " não possui unidades disponíveis suficiente em estoque");
    }
}
