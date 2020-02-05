package com.equipemovies.ecommercemovies.domain;

import java.io.Serializable;

public class GeneroCliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String genero;
	
	//Construtor
	public GeneroCliente() {
		
	}
	
	//Construtor com dados
	public GeneroCliente(Integer id, String genero) {
		super();
		this.id = id;
		this.genero = genero;
	}

	//Getters e Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeneroCliente other = (GeneroCliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		return true;
	}
	
	

}
