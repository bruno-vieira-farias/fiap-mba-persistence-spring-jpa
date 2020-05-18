package br.com.fiap.mba.persistence.spring.persistence.domain.repository;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Estoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstoqueRepository extends CrudRepository<Estoque, Integer> {

    Estoque findByProduto(Produto produto);

    void removeItemEstoqueByProduto(Produto produto);

    List<Estoque> findAll();

}