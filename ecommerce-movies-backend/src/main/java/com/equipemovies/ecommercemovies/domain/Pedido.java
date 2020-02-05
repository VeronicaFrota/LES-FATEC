package com.equipemovies.ecommercemovies.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Pedido extends EntidadeDominio {

    private static final long serialVersionUID = 1L;

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("id_cliente")
    private Integer idCliente;

    @JsonProperty("id_filme")
    private Integer idFilme;

    @JsonProperty("id_cupom")
    private Integer idCupom;

    @JsonProperty("id_cartao")
    private Integer idCartao;

    @JsonProperty("id_endereco")
    private Integer idEndereco;

    @JsonProperty("id_transacao")
    private Integer idTransacao;

    @JsonProperty("quantidade_comprada")
    private Integer quantidadeComprada;

    @JsonProperty("quantidade_parcela")
    private Integer quantidadeParcela;

    @JsonProperty("valor_frete")
    private Float valorFrete;

    @JsonProperty("total_compra")
    private Float totalCompra;

    @JsonProperty("subtotal_compra")
    private Float subtotalCompra;

    // @JsonFormat(pattern = "dd/MM/yyyy HH:mm") // Date Format Mask
    @JsonProperty("data_cadastro")
    private Date dataCadastro;

    private Boolean status;

    private List<Filme> filmes;

    private List<Cartao> cartoes;

    public Pedido() {

    }

    public Pedido(Integer id, Integer idCliente, Integer idFilme, Integer idCupom, Integer idCartao, Integer idTransacao,
                  Integer quantidadeComprada, Integer quantidadeParcela, Float valorFrete, Float totalCompra,
                  Float subtotalCompra, Date dataCadastro, Boolean status, List<Filme> filmes) {
        this.id = id;
        this.idCliente = idCliente;
        this.idFilme = idFilme;
        this.idCupom = idCupom;
        this.idCartao = idCartao;
        this.idTransacao = idTransacao;
        this.quantidadeComprada = quantidadeComprada;
        this.quantidadeParcela = quantidadeParcela;
        this.valorFrete = valorFrete;
        this.totalCompra = totalCompra;
        this.subtotalCompra = subtotalCompra;
        this.dataCadastro = dataCadastro;
        this.status = status;
        this.filmes = filmes;
    }

    // GETTERS AND SETTERS
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Integer idFilme) {
        this.idFilme = idFilme;
    }

    public Integer getIdCupom() {
        return idCupom;
    }

    public void setIdCupom(Integer idCupom) {
        this.idCupom = idCupom;
    }

    public Integer getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Integer idCartao) {
        this.idCartao = idCartao;
    }

    public Integer getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Integer idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Integer getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(Integer quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public Integer getQuantidadeParcela() {
        return quantidadeParcela;
    }

    public void setQuantidadeParcela(Integer quantidadeParcela) {
        this.quantidadeParcela = quantidadeParcela;
    }

    public Float getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Float valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Float getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Float totalCompra) {
        this.totalCompra = totalCompra;
    }

    public Float getSubtotalCompra() {
        return subtotalCompra;
    }

    public void setSubtotalCompra(Float subtotalCompra) {
        this.subtotalCompra = subtotalCompra;
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

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }
}
