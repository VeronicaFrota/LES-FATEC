package com.equipemovies.ecommercemovies.DAO;

import java.sql.SQLException;
import java.util.List;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;

public interface IDAO {
	
	//Geral para cadastro de filme e cliente
	public void salvar(EntidadeDominio entidade) throws SQLException;

	//Geral para alterar dados do filme e dados pessoais do cliente
	public void alterar(EntidadeDominio entidade) throws SQLException;

	//Geral para exclusão do filme e do cliente em si
	public void excluir(EntidadeDominio entidade) throws SQLException;
	
	//Geral para listar dados do filme e dados pessoais do cliente
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException;

}
