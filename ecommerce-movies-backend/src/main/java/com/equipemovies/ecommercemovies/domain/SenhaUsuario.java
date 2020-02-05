package com.equipemovies.ecommercemovies.domain;

import java.util.Date;

public class SenhaUsuario extends EntidadeDominio{
	
	private Integer id;
	private String senha;
	
	//Costrutor
	public SenhaUsuario() {
	}
		
		//Construtor com dados
	public SenhaUsuario(Integer id, String senha) {
		super();
		this.id = id;
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
