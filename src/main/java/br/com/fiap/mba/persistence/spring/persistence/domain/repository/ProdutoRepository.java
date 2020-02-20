package br.com.fiap.mba.persistence.spring.persistence.domain.repository;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Produto findByCodigo(String codigo);
}

