package br.com.fiap.mba.persistence.spring.persistence.domain.repository;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

    Produto findByCodigo(String codigo);

    List<Produto> findAll();
}

