package br.com.tech4me.vendams.compartilhado;

import java.time.LocalDate;
import java.util.List;

public class VendaDto {
    private String id;
    private String codigo;
    private Integer quantidade;
    private LocalDate dataVenda;
    private Float valorVenda;
    private String produtoVenda;
    private List<Produto> produtos;
    
    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public LocalDate getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
    public Float getValorVenda() {
        return valorVenda;
    }
    public void setValorVenda(Float valorVenda) {
        this.valorVenda = valorVenda;
    }
    public String getProduto() {
        return produtoVenda;
    }
    public void setProduto(String produto) {
        this.produtoVenda = produto;
    }
}
