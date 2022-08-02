package br.com.tech4me.vendams.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.vendams.clienteHTTP.ProdutoFeingClient;
import br.com.tech4me.vendams.compartilhado.VendaDto;
import br.com.tech4me.vendams.model.Venda;
import br.com.tech4me.vendams.repository.VendaRepositorio;


@Service
public class VendaServiceImpl implements VendaService {
    
    @Autowired
    private VendaRepositorio repo;

    @Autowired
    private ProdutoFeingClient produtoMsClient;

    @Override
    public VendaDto lancarVenda(VendaDto venda){
        return salvarVenda(venda);
    }

    @Override
    public List<VendaDto> obterTodos(){
        List<Venda> venda = repo.findAll();

        return venda.stream()
        .map(vendas -> new ModelMapper().map(vendas,VendaDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public Optional<VendaDto> obterPorId(String id){
        Optional<Venda> venda =repo.findById(id);

        if(venda.isPresent()){
            VendaDto dto = new ModelMapper().map(venda.get(),VendaDto.class);
            dto.setProdutos(produtoMsClient.obterProdutos(id));
            return Optional.of(dto);
        }
        return Optional.empty();
    }
    
    private VendaDto salvarVenda(VendaDto venda){
        ModelMapper mapper = new ModelMapper();
        Venda vendaEntidade = mapper.map(venda, Venda.class);
        vendaEntidade = repo.save(vendaEntidade);

        return mapper.map(vendaEntidade,VendaDto.class);
    }
}