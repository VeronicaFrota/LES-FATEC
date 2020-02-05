package com.equipemovies.ecommercemovies.DAO;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Filme;

import org.springframework.jdbc.core.JdbcTemplate;
import com.equipemovies.ecommercemovies.domain.GeneroPie;

import java.sql.SQLException;
import java.util.List;

public class GeneroPieDAO implements IDAO{

	private JdbcTemplate jdbcTemplate;
	public int Mes;
	
	public GeneroPieDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		
		//Primeira query a ser executada, retorna todos os generos presentes no sistema
		String query = "SELECT * FROM genero";
		
		GeneroPie generoMes = (GeneroPie) entidade;
		
		System.out.println(generoMes.getMes());
		
		return jdbcTemplate.query(query, (rs, i) -> {
			
			GeneroPie genero = new GeneroPie();
				
				//Query que pega a quantidade de filmes vendidos no mes atual pelo genero
				String queryQuantidade = "SELECT COUNT(id_genero) AS total_genero FROM filme as f " + 
						"JOIN produto AS p ON f.id = p.id_filme " + 
						"WHERE MONTH(p.data_cadastro) = " + generoMes.getMes() + " AND id_genero = " + rs.getInt("id");
				
				
				jdbcTemplate.query(queryQuantidade, new Object[] { }, (rsQuantidade, j) -> {
					System.out.println("Entrou Query Quantidade");					
					genero.setTotalGenero(rsQuantidade.getInt("total_genero"));
					
					//Define os valores que serão definidos para apresentação
					genero.setId(rs.getInt("id"));
					genero.setNome(rs.getString("nome"));

					return genero;
				});

			return genero;
		});
	}

}
