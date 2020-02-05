package com.equipemovies.ecommercemovies.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;

import com.equipemovies.ecommercemovies.domain.*;
import com.mysql.jdbc.Statement;

public class UsuarioDAO implements IDAO{
	
	private JdbcTemplate jdbcTemplate;

    public UsuarioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	//Função que salva as informações do cliente
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		
		//Realizando um cast da entidade para o usuário
		Usuario usuario = (Usuario) entidade;
		KeyHolder key = new GeneratedKeyHolder();
		Endereco endereco = usuario.getEndereco().get(0);
		
		
		//Defininco query para inserção do registro do cliente 
		String query = "INSERT INTO cliente(data_cadastro, nome, cpf, data_nascimento, email, "
		 		+ "senha, genero, telefone, status) "
		 		+ "VALUES (?,?,?,?,?,?,?,?,?)";
		
		int update = jdbcTemplate.update(new PreparedStatementCreator() {

		      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		        final PreparedStatement ps = connection.prepareStatement(query, 
		            Statement.RETURN_GENERATED_KEYS);
		        ps.setDate(1, new java.sql.Date(usuario.getDataCadastro().getTime()));
		        ps.setString(2, usuario.getNome());
		        ps.setString(3, usuario.getCpf());
		        ps.setString(4, usuario.getDataNascimento());
		        ps.setString(5, usuario.getEmail());
		        ps.setString(6, usuario.getSenha());
		        ps.setString(7, usuario.getGenero());
		        ps.setString(8, usuario.getTelefone());
		        ps.setInt(9, 1);
		        return ps;
		      }
		}, key);
		
		//Insere no banco informações do endereco do cliente
		String queryEnd = "INSERT INTO endereco(id_cliente, id_cidade, logradouro, numero, bairro, cep, "
				+ "observacao, data_cadastro, status) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		
		jdbcTemplate.update(queryEnd, new Object[] {
				key.getKey().longValue(),
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

	//Função que altera as informações do cliente
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Usuario usuario = (Usuario) entidade;
		
		String query = "UPDATE cliente SET nome = ?, cpf = ?, data_nascimento = ?, email = ?, "
				+ "genero = ?, telefone = ?"
				+ "WHERE id = ? ";
		
		jdbcTemplate.update(query, new Object[] {
			usuario.getNome(),
			usuario.getCpf(),
			usuario.getDataNascimento(),
			usuario.getEmail(),
			usuario.getGenero(),
			usuario.getTelefone(),
			usuario.getId(),
		});
	}

	//Função que exclui o registro do cliente
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		Usuario usuario = (Usuario) entidade;
		
		String query = "UPDATE cliente SET status = 0 WHERE id = ?";
		
		jdbcTemplate.update(query, new Object[]{
			usuario.getId()
		});
		
	}

	//Função que lista o registro do cliente
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {

		Usuario usuarioCast = (Usuario) entidade;

		final String query;
		// Procura na base um email e senha que combine
		if(entidade.getId() != null)
			query = "SELECT * FROM cliente WHERE id=" + entidade.getId().toString();
		else if (!StringUtils.isEmpty(usuarioCast.getEmail()) && !StringUtils.isEmpty(usuarioCast.getSenha())) {
			String email = usuarioCast.getEmail().replace("'", "").replace("\\'", "");
			String senha = usuarioCast.getSenha().replace("'", "").replace("\\'", "");
			query = "SELECT * FROM cliente WHERE email='" + email + "' AND senha='" + senha + "'";
		}
		else
			query = "SELECT * FROM cliente";

		return jdbcTemplate.query(query, (rs, i) -> {
			Usuario usuario = new Usuario();
			
			//Pega as informações de clientes que serão apresentadas em tela
			usuario.setId(rs.getInt("id"));
			usuario.setDataCadastro(rs.getDate("data_cadastro"));
			usuario.setNome(rs.getString("nome"));
			usuario.setCpf(rs.getString("cpf"));
			usuario.setDataNascimento(rs.getString("data_nascimento"));
			usuario.setEmail(rs.getString("email"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setGenero(rs.getString("genero")); 
			usuario.setStatus(rs.getBoolean("status"));
			usuario.setTelefone(rs.getString("telefone"));
			
			//Pega as informações do endereco do cliente
			String queryEnd = "SELECT * FROM endereco WHERE id_Cliente = ?";
			
			List<Endereco> enderecos = jdbcTemplate.query(queryEnd, new Object[]{
					usuario.getId()
			}, (rsEnd, j) -> {
				Endereco endereco = new Endereco();
				
				endereco.setId(rsEnd.getInt("id"));
				endereco.setIdCidade(rsEnd.getInt("id_cidade"));
				endereco.setIdCliente(rsEnd.getInt("id_cliente"));
				endereco.setLogradouro(rsEnd.getString("logradouro"));
				endereco.setNumero(rsEnd.getString("numero"));
				endereco.setBairro(rsEnd.getString("bairro"));
				endereco.setCep(rsEnd.getString("cep"));
				endereco.setObservacao(rsEnd.getString("observacao"));
				endereco.setDataCadastro(rsEnd.getDate("data_cadastro"));
				endereco.setStatus(rsEnd.getBoolean("status"));
				
				return endereco;
			});
			
			usuario.setEndereco(new ArrayList<>(enderecos));

			//Pega as informações do cartão do cliente
			String queryCartao = "SELECT * FROM cartao WHERE id_cliente = ?";

			List<Cartao> cartoes = jdbcTemplate.query(queryCartao, new Object[]{
					usuario.getId()
			}, (rsCartao, j) -> {
				Cartao cartao = new Cartao();

				cartao.setId(rsCartao.getInt("id"));
				cartao.setIdBandeira(rsCartao.getInt("id_bandeira"));
				cartao.setNumeroCartao(rsCartao.getString("numero_cartao"));
				cartao.setCvv(rsCartao.getString("cvv"));
				cartao.setDataValidade(rsCartao.getString("data_validade"));

				return cartao;
			});

			usuario.setCartoes(cartoes);
			
			return usuario;
		});
	}

}
