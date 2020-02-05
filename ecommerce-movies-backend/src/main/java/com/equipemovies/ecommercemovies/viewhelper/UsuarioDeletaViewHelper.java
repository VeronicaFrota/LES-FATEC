	package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Usuario;
import com.equipemovies.ecommercemovies.util.Resultado;

public class UsuarioDeletaViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("excluir")
				&& request.getParameter("id_cliente") != null) {
			
			Usuario usuario = new Usuario();
			
			//Pega o id do usuario
			String id = request.getParameter("id_cliente");
			usuario.setId(Integer.parseInt(id));
			
			usuario.setStatus(false);
			
			return usuario;
		
		}
		
		return null;
	}

	//Inutilizado
	@Override
	public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
