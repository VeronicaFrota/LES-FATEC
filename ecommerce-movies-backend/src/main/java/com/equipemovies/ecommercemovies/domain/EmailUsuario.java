package com.equipemovies.ecommercemovies.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailUsuario extends EntidadeDominio {
	
private static final long serialVersionUID = 1L;
	private String email;

	//Costrutor
	public EmailUsuario() {
	}
	
	//Construtor com dados
	public EmailUsuario(String email) {
		super();

		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
