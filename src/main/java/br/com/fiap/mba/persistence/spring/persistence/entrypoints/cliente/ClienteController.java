package br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Cliente;
import br.com.fiap.mba.persistence.spring.persistence.domain.exception.ClienteJaExisteException;
import br.com.fiap.mba.persistence.spring.persistence.domain.exception.ClienteNaoEncontradoException;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.cliente.ClienteService;
import br.com.fiap.mba.persistence.spring.persistence.domain.services.cliente.EspecificacaoCliente;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente.dto.ClienteDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente.dto.EnderecoDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/cliente")
@Api(description = "Entrypoint para manipulação de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping()
    @ApiOperation(value = "Realiza o cadastro de um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Cliente já existe")}
            )
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastraCliente(@RequestBody ClienteDto clienteDto) {
        try {
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
        } catch (ClienteJaExisteException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/{cpf}")
    @ApiOperation(value = "Realiza a consulta de um cadastro de cliente identificado pelo cpf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente obtido com sucesso"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")})
    public ClienteDto buscaCliente(@PathVariable String cpf) {

        try {
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
        } catch (ClienteNaoEncontradoException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping()
    @ApiOperation(value = "Altera os dados de um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente alterado com sucesso"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")})
    public void alteraCliente(@RequestBody EspecificacaoCliente especificacaoCliente) {
        try {
            clienteService.alteraCliente(especificacaoCliente);
        } catch (ClienteNaoEncontradoException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cpf}")
    @ApiOperation(value = "Remove um cadastro de cliente identificado pelo cpf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente removido com sucesso"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")})
    public void removeCliente(@PathVariable String cpf) {
        try {
            clienteService.removeCliente(cpf);
        } catch (ClienteNaoEncontradoException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}