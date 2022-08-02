package br.com.tech4me.vendams.clienteHTTP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.tech4me.vendams.compartilhado.ProduProduto;

@FeignClient(name= "animais-ms", fallback = AnimaisFeignClientFallback.class)
public interface AnimaisFeignClient {
    @GetMapping(path = "/api/animais/{dono}/lista")
    List<Produto> obterAnimais(@PathVariable String dono);   
}

@Component
class AnimaisFeignClientFallback implements AnimaisFeignClient {

    @Override
    public List<Produtooduto> obterProdutos(String nome){
            return new ArrayList<>();
        }
    }