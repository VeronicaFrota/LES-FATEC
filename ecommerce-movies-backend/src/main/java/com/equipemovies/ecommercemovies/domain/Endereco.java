package com.equipemovies.ecommercemovies.domain;

import java.util.Date;

public class Endereco extends EntidadeDominio{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer idCliente;
	private Integer idCidade;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	private String observacao;
	private Date dataCadastro;
	private Boolean status;
	
	//Construtor sem dado
	public Endereco() {
		
	}
	
	//Construtor com dados
	public Endereco(Integer id, Integer idCliente, Integer idCidade, String logradouro, String numero,
			String bairro, String cep, String observacao, Date dataCadastro,
			Boolean status) {
		super();
		this.idCidade = idCidade;
		this.id = id;
		this.idCliente = idCliente;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.observacao = observacao;
		this.dataCadastro = dataCadastro;
		this.status = status;
	}


	//Getters e Setters
	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public Date getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

	
	
	
	
}
