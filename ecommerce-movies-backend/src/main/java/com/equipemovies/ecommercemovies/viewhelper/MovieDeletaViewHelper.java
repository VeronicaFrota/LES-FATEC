package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Filme;
import com.equipemovies.ecommercemovies.domain.Genero;
import com.equipemovies.ecommercemovies.util.Resultado;

public class MovieDeletaViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {

		if(request.getParameter("operacao") != null
		&& request.getParameter("operacao").toLowerCase().equals("excluir")
		&& request.getParameter("id_filme") != null) {
			
			Filme filme = new Filme();

			// para pegar o id do filme
			String id = request.getParameter("id_filme");
			filme.setId(Integer.parseInt(id));

			filme.setStatus(false);
			
			return filme;
		}
		
		return null;
	}

	@Override
	public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
