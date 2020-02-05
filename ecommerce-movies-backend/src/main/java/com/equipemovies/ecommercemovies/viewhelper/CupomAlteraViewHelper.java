package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.equipemovies.ecommercemovies.domain.Cupom;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;

public class CupomAlteraViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Cupom cupom = new Cupom();
		
		if (request.getParameter("operacao") != null
		           && request.getParameter("operacao").equals("alterar")
		           && request.getParameter("id") != null ) {
			
			cupom.setStatus(false);
			cupom.setId(Integer.valueOf(request.getParameter("id")));
		}
		
		return cupom;
	}

	@Override
	public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {
		if (result.getMsg() != null) {
			return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
		}
		return null;
	}

}
