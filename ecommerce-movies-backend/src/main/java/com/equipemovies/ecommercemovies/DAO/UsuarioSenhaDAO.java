package com.equipemovies.ecommercemovies.DAO;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.GeneroBar;
import com.equipemovies.ecommercemovies.domain.SenhaUsuario;
import com.equipemovies.ecommercemovies.domain.Usuario;

public class UsuarioSenhaDAO implements IDAO{

	private JdbcTemplate jdbcTemplate;

    public UsuarioSenhaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		SenhaUsuario usuario = (SenhaUsuario) entidade;
		String query = "UPDATE cliente SET senha = ? WHERE id = ?";
		
		System.out.println(query);
		
		jdbcTemplate.update(query, new Object[] {
				usuario.getSenha(),
				usuario.getId()
		});
	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		SenhaUsuario usuario = (SenhaUsuario) entidade;
		SenhaUsuario usuarioRet = new SenhaUsuario();
		String query = "SELECT senha FROM cliente WHERE id = " + usuario.getId();
		
		String senha = usuario.getSenha();
		
		return jdbcTemplate.query(query, (rs, i) -> {
			//Verifica se a senha inserida como atual é igual a atual
			if(rs.getString("senha").equals(senha)) {
				usuarioRet.setSenha("Correta");
				return usuarioRet;
			}
			else {
				usuarioRet.setSenha("Incorreta");
				return usuarioRet;
			}
		});
	}

}
