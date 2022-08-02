package br.com.tech4me.produtoms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.produtoms.compartilhado.ProdutoDto;
import br.com.tech4me.produtoms.model.Produto;
import br.com.tech4me.produtoms.repository.ProdutoRepositorio;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    
    @Autowired
    private ProdutoRepositorio repo;

    @Override
    public ProdutoDto incluirProduto(ProdutoDto produto){
        return salvarProduto(produto);
    }

    @Override
    public List<ProdutoDto> obterTodos(){
        List<Produto> produtos = repo.findAll();
         return produtos.stream()
         .map(produto -> new ModelMapper().map(produto,ProdutoDto.class))
         .collect(Collectors.toList());

    }

    @Override
    public Optional<ProdutoDto> obterPorId(String id){
        Optional<Produto> produto = repo.findById(id);
        if(produto.isPresent()){
            return Optional.of(new ModelMapper().map(produto.get(),ProdutoDto.class));
        }
        return Optional.empty();
    }

    @Override
    public void removerProduto(String id){
        repo.deleteById(id);
    }

    @Override
    public ProdutoDto atualizarProduto(String id, ProdutoDto produto){
        produto.setId(id);
        return salvarProduto(produto);
    }

    private ProdutoDto salvarProduto(ProdutoDto produto){
        ModelMapper mapper = new ModelMapper();
        Produto produtoentidade = mapper.map(produto, Produto.class);
        produtoentidade = repo.save(produtoentidade);

        return mapper.map(produtoentidade,ProdutoDto.class);
    }
}