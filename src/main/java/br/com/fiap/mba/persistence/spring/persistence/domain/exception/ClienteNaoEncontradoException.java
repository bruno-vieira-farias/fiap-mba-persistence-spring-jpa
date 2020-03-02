package br.com.fiap.mba.persistence.spring.persistence.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClienteNaoEncontradoException extends Exception {
    public ClienteNaoEncontradoException(String cpf) {
        super("O cliente com o cpf " + cpf + "não foi econtrado.");
    }
}
