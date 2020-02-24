package br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente;

public class ClienteDto {
    private String nome;
    private String cpf;
    private EnderecoDto endereco;

    public ClienteDto(String nome, String cpf, EnderecoDto endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDto endereco) {
        this.endereco = endereco;
    }
}
