package com.equipemovies.ecommercemovies.DAO;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.equipemovies.ecommercemovies.domain.EmailUsuario;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.SenhaUsuario;
import com.equipemovies.ecommercemovies.domain.Usuario;

public class UsuarioEmailDAO implements IDAO{

	private JdbcTemplate jdbcTemplate;

    public UsuarioEmailDAO(JdbcTemplate jdbcTemplate) {
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
		EmailUsuario usuario = (EmailUsuario) entidade;
		EmailUsuario usuarioRet = new EmailUsuario();
		
		String query = "SELECT COUNT(email) as email FROM cliente WHERE email = '" + usuario.getEmail() + "'";
		
		String email = usuario.getEmail();
		
		return jdbcTemplate.query(query, (rs, i) -> {
			//Verifica se o email inserido já existe
			if(rs.getInt("email") > 0){
				usuarioRet.setEmail("Existente");
				return usuarioRet;
			}
			else{
				usuarioRet.setEmail("Novo");
				return usuarioRet;
			}
		});
	}

}
