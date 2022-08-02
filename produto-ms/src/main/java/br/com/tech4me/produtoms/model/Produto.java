package br.com.tech4me.produtoms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("produto")
public class Produto {

    @Id
    private String id;
    private String nome;
    private String codigo;
    private float preco;
    private Integer estoque;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }
    public Integer getEstoque() {
        return estoque;
    }
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}