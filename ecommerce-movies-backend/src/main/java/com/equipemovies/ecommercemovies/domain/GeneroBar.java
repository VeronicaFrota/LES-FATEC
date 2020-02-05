package com.equipemovies.ecommercemovies.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneroBar extends EntidadeDominio{
	
	private String comeco;
	private String fim;
	private String ano;
	private Integer id;
	
	@JsonProperty("label")
	private String nome;
	
	private List<Integer> data;
	private List<String> dataAno;

	private int totalGenero;

	// Constructor
	public GeneroBar() {

	}

	// Constructor with data
	public GeneroBar(Integer id, String nome, int totalGenero, List<Integer> data, String comeco, String fim, List<String> dataAno, String ano) {
		super();
		this.id = id;
		this.comeco = comeco;
		this.fim = fim;
		this.data = data;
		this.nome = nome;
		this.totalGenero = totalGenero;
		this.dataAno = dataAno;
		this.ano = ano;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public List<Integer> getData() {
		return data;
	}

	public List<String> getDataAno() {
		return dataAno;
	}

	public void setDataAno(List<String> dataAno) {
		this.dataAno = dataAno;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

	// Getters and Setter
	public Integer getId() {
		return id;
	}

	public String getComeco() {
		return comeco;
	}

	public void setComeco(String comeco) {
		this.comeco = comeco;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
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
		GeneroBar other = (GeneroBar) obj;
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
