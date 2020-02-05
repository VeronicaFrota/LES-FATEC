package com.equipemovies.ecommercemovies.DAO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.equipemovies.ecommercemovies.domain.Endereco;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;

public class EnderecoDAO implements IDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public EnderecoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	//Metodo que salva os dados de endereço do cliente
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		Endereco endereco = (Endereco) entidade;
		
		String query = "INSERT INTO endereco (id_cliente, id_cidade, logradouro, numero, bairro, cep, "
				+ "observacao, data_cadastro, status) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		
		jdbcTemplate.update(query, new Object[] {
				endereco.getIdCliente(),
				endereco.getIdCidade(),
				endereco.getLogradouro(),
				endereco.getNumero(),
				endereco.getBairro(),
				endereco.getCep(),
				endereco.getObservacao(),
				new Timestamp(endereco.getDataCadastro().getTime()),
				1
			});
	}

	//Metodo que faz a alteração dos dados de endereço do cliente
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Endereco endereco = (Endereco) entidade;
		
		String query = "UPDATE endereco SET id_cidade = ?, logradouro = ?, numero = ?, bairro = ?, "
				+ "cep = ?, observacao = ?"
				+ "WHERE id = ?";
		
		jdbcTemplate.update(query, new Object[] {
				endereco.getIdCidade(),
				endereco.getLogradouro(),
				endereco.getNumero(),
				endereco.getBairro(),
				endereco.getCep(),
				endereco.getObservacao(),
				endereco.getId()
		});
		
	}

	//Metodo que faz a exclusão do endereço do cliente
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		
		Endereco endereco = (Endereco) entidade;
		
		String query = "UPDATE endereco SET status = 0 WHERE id = ?";
		
		jdbcTemplate.update(query, new Object[] {
			endereco.getId()	
		});
		
	}

	//Metodo que lista os endereços de determinado cliente
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		Endereco enderecoBusca = (Endereco)entidade;
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM endereco WHERE 1=1");
		
		if(enderecoBusca.getId() != null) {
			query.append(" AND id=" + enderecoBusca.getId());
		}
		
		if(enderecoBusca.getIdCliente() != null) {
			query.append(" AND id_cliente=" + enderecoBusca.getIdCliente());
		}
		
		System.out.println(query);
		
		return jdbcTemplate.query(query.toString(), (rs, i) -> {
			Endereco endereco = new Endereco();	
			//Pega as informações de endereco que serão apresentados em tela
			endereco.setId(rs.getInt("id"));
			endereco.setIdCliente(rs.getInt("id_cliente"));
			endereco.setIdCidade(rs.getInt("id_cidade"));
			endereco.setLogradouro(rs.getString("logradouro"));
			endereco.setNumero(rs.getString("numero"));
			endereco.setBairro(rs.getString("bairro"));
			endereco.setCep(rs.getString("cep"));
			endereco.setObservacao(rs.getString("observacao"));
			endereco.setDataCadastro(rs.getDate("data_cadastro"));
			endereco.setStatus(rs.getBoolean("status"));

			System.out.println(endereco.getIdCidade());
			System.out.println(endereco.getLogradouro());
			System.out.println(endereco.getNumero());
			System.out.println(endereco.getBairro());
			System.out.println(endereco.getCep());
			System.out.println(endereco.getObservacao());
			
			return endereco;
			
		});
		
	}

}
