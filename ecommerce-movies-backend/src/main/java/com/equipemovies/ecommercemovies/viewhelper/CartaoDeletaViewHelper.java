package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.equipemovies.ecommercemovies.domain.Cartao;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.Resultado;

public class CartaoDeletaViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		if (request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("excluir")
				&& request.getParameter("id_cartao") != null) {
			
			Cartao cartao = new Cartao();
			
			String id = request.getParameter("id_cartao");
			cartao.setId(Integer.valueOf(id));
			
			cartao.setStatus(false);
			
			return cartao;
		}
			
		return null;
	}

	@Override
	public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
