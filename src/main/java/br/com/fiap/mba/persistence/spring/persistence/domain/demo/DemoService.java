package br.com.fiap.mba.persistence.spring.persistence.domain.demo;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private final CadastroClienteDemo cadastroClienteDemo;
    private final CadastroProdutosDemo cadastroProdutosDemo;

    public DemoService(CadastroClienteDemo cadastroClienteDemo, CadastroProdutosDemo cadastroProdutosDemo) {
        this.cadastroClienteDemo = cadastroClienteDemo;
        this.cadastroProdutosDemo = cadastroProdutosDemo;
    }

    /**
     * Gera uma massa de dados para demosntração do funcionamento e manipulção da s apis.
     */
    public void geraCargaInicialDemonstracao() {
        cadastroClienteDemo.cadastraClienteDemo();
        cadastroProdutosDemo.cadastraProdutosDemo();
        //Colocar alguns produtos em estoque
        //Criar alguns pedidos
        //retornar os pedidos gerados.
    }


}
