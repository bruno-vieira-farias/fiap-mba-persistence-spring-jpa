package br.com.fiap.mba.persistence.spring.persistence.domain.produto;

import br.com.fiap.mba.persistence.spring.persistence.domain.produto.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

    Produto findByCodigo(String codigo);

    List<Produto> findAll();
}

