package com.equipemovies.ecommercemovies.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Filme;

//para realizar todo o crud
/*public class FilmeDAO extends AbstractJdbcDAO {	

	public FilmeDAO() {
		super("filme", "id_filme");
	}


	/**
	* Realiza a INSERCAO dos dados no banco de dados
	
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {

		PreparedStatement pst = null;
	 	Filme filme = (Filme) entidade;

	 	 try {
	         openConnection();

	         connection.setAutoCommit(false);

	         StringBuilder sql = new StringBuilder();
	         sql.append("INSERT INTO filme(id_filme, nome, codigo_barras, id_genero, id_pais_origem, id_idioma, "
	         		 + "id_classificacao_etaria, ano, snopse, data_cadastro, status)");

	         // status do livro, 1 = ATIVO, 0 = INATIVO
	         sql.append("VALUES (?,?,?,?,?,?,?,?,?,?, 1)");

	         pst = connection.prepareStatement(sql.toString(),
	                 Statement.RETURN_GENERATED_KEYS);

	         pst.setString(1, filme.getNome());
	         pst.setString(2, filme.getCodigoBarras());
	         pst.setLong(3, filme.getGenero().getId());
	         pst.setLong(4, filme.getPaisOrigem().getId());
	         pst.setLong(5, filme.getIdioma().getId());
	         pst.setLong(6, filme.getClassificacaoEtaria().getId());
	         pst.setInt(7, filme.getAno());
	         pst.setString(8, filme.getSnopse());

	         Timestamp time = new Timestamp(filme.getDataCadastro().getTime());
	         pst.setTimestamp(9, time);

	         pst.executeUpdate();

	         ResultSet rs = pst.getGeneratedKeys();

	         int id = 0;
	         if(rs.next()) {
	             id = rs.getInt(1);
	         }

	         filme.setId(id);

	         connection.commit();
	     } catch (Exception e) {
	         // TODO: handle exception
	     }

	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		PreparedStatement pst = null;
	 	Filme filme = (Filme) entidade;

	     try {
	         openConnection();

	         connection.setAutoCommit(false);

	         StringBuilder sql = new StringBuilder();
	         sql.append("UPDATE filme SET  nome=?, codigo_barras=?, id_genero=?, id_pais_origem=?, id_idioma=?, "
	         		 + "id_classificacao_etaria=?, ano=?, snopse=?, data_cadastro=?");
	         sql.append("WHERE id_filme=?");

	         pst = connection.prepareStatement(sql.toString());

	         pst.setString(1, filme.getNome());
	         pst.setString(2, filme.getCodigoBarras());
	         pst.setLong(3, filme.getGenero().getId());
	         pst.setLong(4, filme.getPaisOrigem().getId());
	         pst.setLong(5, filme.getIdioma().getId());
	         pst.setLong(6, filme.getClassificacaoEtaria().getId());
	         pst.setInt(7, filme.getAno());
	         pst.setString(8, filme.getSnopse());
	         

	         pst.setInt(9, filme.getId());

	         pst.executeUpdate();

	         connection.commit();
	     } catch (Exception e) {
	         // TODO: handle exception
	     }
		
	}*/

	/**
	* Realiza a CONSULTA, exibindo os dados em cada campo para que a alterao possa ser realizada
	*/
	/*@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		
		PreparedStatement pst = null;

		try {
			openConnection();

			// Select que faz a selecao de todos os dados presentes dentro da tabela filme
			pst = connection.prepareStatement("SELECT * FROM filme LIMIT 0,20");

			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> filmes = new ArrayList<EntidadeDominio>();

			//DAO de repeticao que pega todas as informacoes dos filmes
			while (rs.next()) {
				Filme filme = new Filme();

				filme.setId(rs.getInt("id"));
				filme.setNome(rs.getString("nome"));
				filme.setCodigoBarras(rs.getString("codigo_barras"));
				filme.setAno(rs.getInt("ano"));
				filme.setSnopse(rs.getString("snopse"));

				filmes.add(filme);
			}

			return filmes;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}*/
