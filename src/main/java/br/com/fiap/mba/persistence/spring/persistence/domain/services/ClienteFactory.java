package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Cliente;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Endereco;
import org.springframework.stereotype.Component;

@Component
public class ClienteFactory {

    public Cliente cria(EspecificacaoCliente especificacaoCliente) {
        Endereco endereco = new Endereco(
                especificacaoCliente.getLogradouro(),
                especificacaoCliente.getNumero(),
                especificacaoCliente.getComplemento(),
                especificacaoCliente.getCep(),
                especificacaoCliente.getCidade(),
                especificacaoCliente.getEstado()
        );

        return new Cliente(
                especificacaoCliente.getNome(),
                especificacaoCliente.getCpf(),
                endereco
        );
    }
}
