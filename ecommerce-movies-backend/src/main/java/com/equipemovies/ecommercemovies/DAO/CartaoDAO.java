package com.equipemovies.ecommercemovies.DAO;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.equipemovies.ecommercemovies.domain.Cartao;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;

public class CartaoDAO implements IDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public CartaoDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		Cartao cartao = (Cartao) entidade;
		
		String query = "INSERT INTO cartao (id_cliente, id_bandeira, numero_cartao, cvv,"
				+ "data_validade, status) VALUES (?,?,?,?,?,?) ";
		
		jdbcTemplate.update(query, new Object[]{
			cartao.getIdCliente(),
			cartao.getIdBandeira(),
			cartao.getNumeroCartao(),
			cartao.getCvv(),
			cartao.getDataValidade(),
			1
		});
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Cartao cartao = (Cartao) entidade;
		
		String query = "UPDATE cartao SET id_bandeira = ?, numero_cartao = ?, "
				+ "cvv = ?, data_validade = ?"
				+ "WHERE id = ?";
		
		jdbcTemplate.update(query, new Object[] {
				cartao.getIdBandeira(),
				cartao.getNumeroCartao(),
				cartao.getCvv(),
				cartao.getDataValidade(),
				cartao.getId()
		});
				
				
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		Cartao cartao = (Cartao) entidade;
		
		String query = "UPDATE cartao SET status = 0 WHERE id = ?";
		
		jdbcTemplate.update(query, new Object[] {
				cartao.getId(),
		});
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		Cartao cartaoBusca = (Cartao) entidade;
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT * FROM cartao WHERE 1=1");
		
		if(cartaoBusca.getId() != null) {
			query.append(" AND id = " + cartaoBusca.getId());
		}
		
		if(cartaoBusca.getIdCliente() != null) {
			query.append(" AND id_cliente = " + cartaoBusca.getIdCliente());
		}
		
		return jdbcTemplate.query(query.toString(), (rs, i) -> {
			Cartao cartao = new Cartao();
			
			cartao.setId(rs.getInt("id"));
			cartao.setIdCliente(rs.getInt("id_cliente"));
			cartao.setIdBandeira(rs.getInt("id_bandeira"));
			cartao.setNumeroCartao(rs.getString("numero_cartao"));
			cartao.setCvv(rs.getString("cvv"));
			cartao.setDataValidade(rs.getString("data_validade"));
			cartao.setStatus(rs.getBoolean("status"));
			
			
			return cartao;
			
			
		});
		
	}

}
