package br.com.tech4me.produtoms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.produtoms.compartilhado.ProdutoDto;
import br.com.tech4me.produtoms.service.ProdutoService;
import br.com.tech4me.produtoms.view.model.ProdutoModeloAlteracao;
import br.com.tech4me.produtoms.view.model.ProdutoModeloInclusao;
import br.com.tech4me.produtoms.view.model.ProdutoModeloResponse;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping(value= "/status")
    public String statusServico(@Value("${local.server.port}") String porta){
        return String.format("Serviço ativo e executando na porta %s",porta);
    }
    
    @PostMapping
    @Valid
    public ResponseEntity<ProdutoModeloResponse> criarProduto(@RequestBody @Valid ProdutoModeloInclusao Produto){
        ModelMapper mapper = new ModelMapper();
        ProdutoDto dto = mapper.map(Produto, ProdutoDto.class);
        dto = service.incluirProduto(dto);
        return new ResponseEntity<>(mapper.map(dto,ProdutoModeloResponse.class),HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ProdutoModeloResponse>> obterTodos(){
        List<ProdutoDto> dtos = service.obterTodos();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ModelMapper mapper = new ModelMapper();
        List<ProdutoModeloResponse> resp = dtos.stream()
        .map(dto -> mapper.map(dto,ProdutoModeloResponse.class))
        .collect(Collectors.toList());
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ProdutoModeloResponse> obterPorId(@PathVariable String id){
        Optional<ProdutoDto> Produto = service.obterPorId(id);
        if(Produto.isPresent()){
            return new ResponseEntity<>(
                new ModelMapper().map(Produto.get(),ProdutoModeloResponse.class),HttpStatus.OK
            );
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable String id){
        service.removerProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ProdutoModeloResponse> atualizarProduto(@PathVariable String id, @Valid @RequestBody ProdutoModeloAlteracao Produto){
        ModelMapper mapper = new ModelMapper();
        ProdutoDto dto = mapper.map(Produto,ProdutoDto.class);
        dto= service.atualizarProduto(id, dto);
        return new ResponseEntity<>(mapper.map(dto,ProdutoModeloResponse.class),HttpStatus.OK);
    }

}