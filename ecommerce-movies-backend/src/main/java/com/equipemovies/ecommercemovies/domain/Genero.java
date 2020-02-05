package com.equipemovies.ecommercemovies.domain;

import java.io.Serializable;

//public class Genero implements Serializable {

public class Genero extends EntidadeDominio {

	//private static final long serialVersionUID = 1L;

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private int totalGenero;

	// Constructor
	public Genero() {

	}

	// Constructor with data
	public Genero(Integer id, String nome, int totalGenero) {
		super();
		this.id = id;
		this.nome = nome;
		this.totalGenero = totalGenero;
	}
	
	// Getters and Setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	// HashCode Equals = To compare objects by value
	// HashCode = generates a numerical code for each object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	// Equals = method that compares two objects
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genero other = (Genero) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


	public int getTotalGenero() {
		return totalGenero;
	}

	public void setTotalGenero(int totalGenero) {
		this.totalGenero = totalGenero;
	}
}
