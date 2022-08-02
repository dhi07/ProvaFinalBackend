package br.com.tech4me.produtoms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.tech4me.produtoms.compartilhado.ProdutoDto;
import br.com.tech4me.produtoms.model.Produto;


@Repository
public interface ProdutoRepositorio extends MongoRepository<Produto, String> {

	List<Produto> findByCodigo(String codigo);
    
}
