package com.equipemovies.ecommercemovies.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Produto extends EntidadeDominio {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @JsonProperty("id_filme")
    private Integer idFilme;

    @JsonProperty("id_pedido")
    private Integer idPedido;

    @JsonProperty("quantidade_comprada")
    private Integer quantidadeComprada;

    // @JsonFormat(pattern = "dd/MM/yyyy HH:mm") // Date Format Mask
    @JsonProperty("data_cadastro")
    private Date dataCadastro;

    private Boolean status;

    public Produto() {

    }

    public Produto(Integer id, Integer idFilme, Integer idPedido, Integer quantidadeComprada, Date dataCadastro, Boolean status) {
        this.id = id;
        this.idFilme = idFilme;
        this.idPedido = idPedido;
        this.quantidadeComprada = quantidadeComprada;
        this.dataCadastro = dataCadastro;
        this.status = status;
    }

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

    public Integer getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(Integer quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public Boolean getStatus() {
        return status;
    }

    @Override
    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
}
