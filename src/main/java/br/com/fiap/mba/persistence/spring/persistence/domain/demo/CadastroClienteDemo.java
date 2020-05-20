package br.com.fiap.mba.persistence.spring.persistence.domain.demo;

import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.Cliente;
import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.ClienteRepository;
import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.Endereco;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Component
public class CadastroClienteDemo {

    private ClienteRepository clienteRepository;

    public CadastroClienteDemo(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public void cadastraClienteDemo() {
        List<Cliente> clientes = Arrays.asList(
                new Cliente("Raimundo da Silva", "12345678910", new Endereco("Rua do sucesso", 10, null, "032212222", "São Paulo", "SP")),
                new Cliente("Maria da Penha", "11122233344", new Endereco("Av. Joao Agressor", 10, null, "5541155415", "Picobinha", "CE")),
                new Cliente("Monteiro Lobato", "2233445566", new Endereco("Av. do pica pau amarelo", 10, null, "5541155415", "Junquinha", "BA")),
                new Cliente("Ernesto Ferreira Lopes", "18273645638", new Endereco("Trav. Atravessada", 10, null, "5541155415", "Sáo Caetano", "SP"))
        );

        clienteRepository.saveAll(clientes);
    }
}