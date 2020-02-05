package com.equipemovies.ecommercemovies.domain;

public class Cartao extends EntidadeDominio {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer idCliente;
	private Integer idBandeira;
	private String numeroCartao;
	private String dataValidade;
	private String quantidadeParcela;
	private String cvv;
	private Boolean status;
	
	//Construtor sem dados
	public Cartao() {
		
	}
	
	//Construtor com dados
	public Cartao(Integer id, Integer idCliente, Integer idBandeira,
			String numeroCartao, String dataValidade, String cvv, Boolean status) {
		
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.idBandeira = idBandeira;
		this.numeroCartao = numeroCartao;
		this.dataValidade = dataValidade;
		this.cvv = cvv;
		this.status = status;
		
	}

	//Getters e Setters
	
	public Integer getId() {
		return id;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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

	public Integer getIdBandeira() {
		return idBandeira;
	}

	public void setIdBandeira(Integer idBandeira) {
		this.idBandeira = idBandeira;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(String quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}
}
