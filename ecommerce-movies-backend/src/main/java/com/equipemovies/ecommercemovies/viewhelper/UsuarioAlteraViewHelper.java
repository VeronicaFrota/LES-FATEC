package com.equipemovies.ecommercemovies.viewhelper;

import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;
import org.springframework.http.ResponseEntity;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioAlteraViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Usuario usuario = new Usuario();
		
		System.out.println(request.getParameter("operacao"));
		
		if (request.getParameter("operacao") != null
		           && request.getParameter("operacao").equals("alterar")
		           && request.getParameter("id_cliente") != null ) {
			
			String nome = request.getParameter("nome");
			String cpf = request.getParameter("cpf");
			String dataNascimento = request.getParameter("dataNascimento");
			String email = request.getParameter("email");
			String genero = request.getParameter("genero");
			String telefone = request.getParameter("telefone");
			
			String id = request.getParameter("id_cliente");
			usuario.setId(Integer.parseInt(id));
			
			usuario.setNome(nome);
			usuario.setCpf(cpf);
			
			//Validação data de nascimento
			if (dataNascimento != null && !dataNascimento.trim().contentEquals("")){
				usuario.setDataNascimento(dataNascimento);
			}
			
			usuario.setEmail(email);
			usuario.setGenero(genero);
			usuario.setTelefone(telefone);
			
			System.out.println(usuario);
		}
		
		return usuario;
	}

	// Pega o resultado
	@Override
	public ResponseEntity getView(Resultado result,
								  HttpServletRequest request,
								  HttpServletResponse response) {

		if (result.getMsg() != null) {
			return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
		}

		return null;
	}


}
