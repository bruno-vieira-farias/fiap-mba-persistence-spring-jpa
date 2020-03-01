package br.com.fiap.mba.persistence.spring.persistence.domain.repository;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Estoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque,Integer> {

    Estoque findByProduto(Produto produto);

    void removeItemEstoqueByProduto(Produto produto);

}