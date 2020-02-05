package com.equipemovies.ecommercemovies.domain;

import java.util.Date;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Filme extends EntidadeDominio {

	private static final long serialVersionUID = 1L;

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@JsonProperty("codigo_barras")
	private String codigoBarras;

	//@OneToOne
	//JoinColunm(name = "genero_id")						// So that the genero ID is the same as the filme
    private Genero genero;

    //@OneToOne
    //@JoinColumn(name = "pais_origem_id")					// So that the pais_origem ID is the same as the filme
    @JsonProperty("pais_origem")
    private PaisOrigem paisOrigem;

    //@OneToOne
    //@JoinColunm(name = "idioma_id")						// So that the idioma ID is the same as the filme
    private Idioma idioma;

    //@OneToOne
    //@JoinColunm(name = "classificacao_etaria_id")			// So that the classificacao_etaria ID is the same as the filme
    @JsonProperty("classificacao_etaria")
    private ClassificacaoEtaria classificacaoEtaria;

	private int ano;

	@JsonProperty("quantidade_comprada")
	private int quantidadeComprada;

	private String sinopse;

	private Estoque estoque;

	// @JsonFormat(pattern = "dd/MM/yyyy HH:mm") // Date Format Mask
	@JsonProperty("data_cadastro")
	private Date dataCadastro;

	private Boolean status;

	private boolean isBuscar;

	// Constructor
	public Filme() {

	}

	// Constructor with data
	public Filme(Integer id, String nome, String codigoBarras, Genero genero, PaisOrigem paisOrigem, Idioma idioma,
				 ClassificacaoEtaria classificacaoEtaria, int ano, int quantidadeComprada, String sinopse, Estoque estoque,
				 Date dataCadastro, Boolean status) {
		super();
		this.id = id;
		this.nome = nome;
		this.codigoBarras = codigoBarras;
		this.genero = genero;
		this.paisOrigem = paisOrigem;
		this.idioma = idioma;
		this.classificacaoEtaria = classificacaoEtaria;
		this.ano = ano;
		this.quantidadeComprada = quantidadeComprada;
		this.sinopse = sinopse;
		this.estoque = estoque;
		this.dataCadastro = dataCadastro;
		this.status = status;
	}

	// Getters and Setter
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
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

	// HashCode Equals = To compare objects by value
	// HashCode = generates a numerical code for each object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoBarras == null) ? 0 : codigoBarras.hashCode());
		result = prime * result + id;
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sinopse == null) ? 0 : sinopse.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Filme other = (Filme) obj;
		if (ano != other.ano)
			return false;
		if (codigoBarras == null) {
			if (other.codigoBarras != null)
				return false;
		} else if (!codigoBarras.equals(other.codigoBarras))
			return false;
		if (id != other.id)
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sinopse == null) {
			if (other.sinopse != null)
				return false;
		} else if (!sinopse.equals(other.sinopse))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public PaisOrigem getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(PaisOrigem paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public ClassificacaoEtaria getClassificacaoEtaria() {
		return classificacaoEtaria;
	}

	public void setClassificacaoEtaria(ClassificacaoEtaria classificacaoEtaria) {
		this.classificacaoEtaria = classificacaoEtaria;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public int getQuantidadeComprada() {
		return quantidadeComprada;
	}

	public void setQuantidadeComprada(int quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}

	public boolean isBuscar() {
		return isBuscar;
	}

	public void setBuscar(boolean buscar) {
		isBuscar = buscar;
	}
}
