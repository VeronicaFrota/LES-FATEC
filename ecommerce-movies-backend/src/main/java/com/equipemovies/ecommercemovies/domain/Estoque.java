package com.equipemovies.ecommercemovies.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Estoque extends EntidadeDominio {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @JsonProperty("id_filme")
    private Integer idFilme;

    private String fornecedor;

    @JsonProperty("quantidade_estoque")
    private int quantidadeEstoque;

    @JsonProperty("valor_compra")
    private float valorCompra;

    @JsonProperty("valor_venda")
    private float valorVenda;

    // @JsonFormat(pattern = "dd/MM/yyyy HH:mm") // Date Format Mask
    @JsonProperty("data_cadastro")
    private Date dataCadastro;

    private Boolean status;

    public Estoque() {
    }

    public Estoque(Integer id, Integer idFilme, String fornecedor, int quantidadeEstoque, float valorCompra,
                   float valorVenda, Date dataCadastro, Boolean status) {
        this.id = id;
        this.idFilme = idFilme;
        this.fornecedor = fornecedor;
        this.quantidadeEstoque = quantidadeEstoque;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.dataCadastro = dataCadastro;
        this.status = status;
    }

    // Getters and Setters
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Integer idFilme) {
        this.idFilme = idFilme;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public float getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(float valorCompra) {
        this.valorCompra = valorCompra;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    @Override
    public Boolean getStatus() {
        return status;
    }

    @Override
    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
