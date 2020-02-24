package br.com.fiap.mba.persistence.spring.persistence.domain.services;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Cliente;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Endereco;
import br.com.fiap.mba.persistence.spring.persistence.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public void cadastraCliente(EspecificacaoCliente especificacaoCliente) {
        Endereco endereco = new Endereco(
                especificacaoCliente.getLogradouro(),
                especificacaoCliente.getNumero(),
                especificacaoCliente.getComplemento(),
                especificacaoCliente.getCep(),
                especificacaoCliente.getCidade(),
                especificacaoCliente.getEstado()
        );

        Cliente cliente = new Cliente(
                especificacaoCliente.getNome(),
                especificacaoCliente.getCpf(),
                endereco
        );

        clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente buscaCliente(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Transactional
    public void alteraCliente(EspecificacaoCliente especificacaoCliente){
        Cliente cliente = clienteRepository.findByCpf(especificacaoCliente.getCpf());

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
    public void removeCliente(String cpf){
        clienteRepository.removeByCpf(cpf);
    }
}