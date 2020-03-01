package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Cliente;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteFactory clienteFactory;

    public ClienteService(ClienteRepository clienteRepository, ClienteFactory clienteFactory) {
        this.clienteRepository = clienteRepository;
        this.clienteFactory = clienteFactory;
    }

    @Transactional
    public void cadastraCliente(EspecificacaoCliente especificacaoCliente) {
        certificaQueClientePodeSerCriado(especificacaoCliente);

        Cliente cliente = clienteFactory.cria(especificacaoCliente);
        clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente buscaCliente(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Transactional
    public void alteraCliente(EspecificacaoCliente especificacaoCliente) {
        Cliente cliente = clienteRepository.findByCpf(especificacaoCliente.getCpf());

        if (cliente == null) {
            throw new IllegalArgumentException("O Cliente que deseja alterar não existe na base de dados.");
        }

        cliente.setNome(especificacaoCliente.getNome());
        cliente.getEndereco().setLogradouro(especificacaoCliente.getLogradouro());
        cliente.getEndereco().setNumero(especificacaoCliente.getNumero());
        cliente.getEndereco().setComplemento(especificacaoCliente.getComplemento());
        cliente.getEndereco().setCep(especificacaoCliente.getCep());
        cliente.getEndereco().setCidade(especificacaoCliente.getCidade());
        cliente.getEndereco().setEstado(especificacaoCliente.getEstado());

        clienteRepository.save(cliente);
    }

    @Transactional
    public void removeCliente(String cpf) {
        clienteRepository.removeByCpf(cpf);
    }

    private void certificaQueClientePodeSerCriado(EspecificacaoCliente especificacaoCliente) {
        if (clienteRepository.findByCpf(especificacaoCliente.getCpf()) != null) {
            throw new IllegalArgumentException("Já existe um cliente com o cpf cadastrado.");
        }
    }
}