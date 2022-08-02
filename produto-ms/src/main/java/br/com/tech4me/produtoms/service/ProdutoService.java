package br.com.tech4me.produtoms.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.produtoms.compartilhado.ProdutoDto;
import br.com.tech4me.produtoms.compartilhado.ProdutoDto;

public interface ProdutoService {
    AnimalDto adicionarProduto(ProdutoDto produto);
    List<ProdutoDto> obterTodos();
    Optional<ProdutoDto> obterPorId(String id);
    List<ProdutoDto> obterPorId(String id);
    void removerProduto(String id);
    ProdutoDto atualizarProduto(String id, ProdutoDto produto);
}
