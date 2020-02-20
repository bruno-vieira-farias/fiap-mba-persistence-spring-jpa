package br.com.fiap.mba.persistence.spring.persistence.domain.repository;

import br.com.fiap.mba.persistence.spring.persistence.domain.entity.ItemEstoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<ItemEstoque,Integer> {

    ItemEstoque findByProduto(Produto produto);

}