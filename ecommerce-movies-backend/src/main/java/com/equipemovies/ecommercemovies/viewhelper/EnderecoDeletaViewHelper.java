package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.equipemovies.ecommercemovies.domain.Endereco;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.Resultado;

public class EnderecoDeletaViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("excluir")
				&& request.getParameter("id_endereco") != null) {
			
			Endereco endereco = new Endereco();
			
			//Pega o id do usuário
			String id = request.getParameter("id_endereco");
			endereco.setId(Integer.parseInt(id));
			
			endereco.setStatus(false);
			
			System.out.println(endereco);
			
			return endereco;
		}
		
		return null;
	}


	@Override
	public ResponseEntity getView(Resultado result,
								  HttpServletRequest request,
								  HttpServletResponse response) {

		return null;
	}

}
