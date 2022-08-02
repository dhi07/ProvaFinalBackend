package br.com.tech4me.vendams.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.vendams.compartilhado.VendaDto;

public interface VendaService {
    VendaDto lancarVenda(VendaDto venda);
    List<VendaDto> obterTodos();
    Optional <VendaDto> obterPorId(String id);
}