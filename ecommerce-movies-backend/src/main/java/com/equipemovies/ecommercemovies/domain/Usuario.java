package com.equipemovies.ecommercemovies.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario extends EntidadeDominio {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Date dataCadastro;
	private String nome;
	private String cpf;
	private String dataNascimento;
	private String email;
	private String senha;
	private String genero;
	private String telefone;
	private Boolean status;
	private ArrayList<Endereco> endereco = new ArrayList<Endereco>();
	private List<Cartao> cartoes = new ArrayList<>();

	//Costrutor
	public Usuario() {
	}
	
	//Construtor com dados
	public Usuario(Integer id, Date dataCadastro, String nome, String cpf,
			String dataNascimento, String email, String senha, String genero, String telefone,
			Boolean status) {
		super();
		this.id = id;
		this.dataCadastro = dataCadastro;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.senha = senha;
		this.genero = genero;
		this.telefone = telefone;
		this.status = status;
//		this.endereco = endereco;
	}

	//Getters e Setters
	public ArrayList<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(ArrayList<Endereco> endereco) {
		this.endereco = endereco;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}
}
