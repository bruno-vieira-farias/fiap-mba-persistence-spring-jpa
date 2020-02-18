package br.com.fiap.mba.persistence.spring.persistence.domain.repository;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
}

