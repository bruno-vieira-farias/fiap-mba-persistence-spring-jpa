package br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Cliente;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.ClienteService;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.EspecificacaoCliente;
import org.springframework.web.bind.annotation.*;

@RestController("cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("cadastra")
    public void cadastraCliente(@RequestBody ClienteDto clienteDto) {
        EspecificacaoCliente especificacaoCliente = new EspecificacaoCliente(
                clienteDto.getNome(),
                clienteDto.getCpf(),
                clienteDto.getEndereco().getLogradouro(),
                clienteDto.getEndereco().getNumero(),
                clienteDto.getEndereco().getComplemento(),
                clienteDto.getEndereco().getCep(),
                clienteDto.getEndereco().getCidade(),
                clienteDto.getEndereco().getEstado()
        );
        clienteService.cadastraCliente(especificacaoCliente);
    }

    @GetMapping("busca/{cpf}")
    public ClienteDto buscaCliente(@PathVariable String cpf) {
        Cliente cliente = clienteService.buscaCliente(cpf);
        return new ClienteDto(
                cliente.getNome(),
                cliente.getCpf(),
                new EnderecoDto(
                        cliente.getEndereco().getLogradouro(),
                        cliente.getEndereco().getNumero(),
                        cliente.getEndereco().getComplemento(),
                        cliente.getEndereco().getCep(),
                        cliente.getEndereco().getCidade(),
                        cliente.getEndereco().getEstado()
                )
        );
    }

    @PutMapping("altera/{cpf}")
    public void alteraCliente(@RequestBody EspecificacaoCliente especificacaoCliente) {
        clienteService.alteraCliente(especificacaoCliente);
    }

    @DeleteMapping("{cpf}")
    public void removeCliente(@PathVariable String cpf) {
        clienteService.removeCliente(cpf);
    }
}
