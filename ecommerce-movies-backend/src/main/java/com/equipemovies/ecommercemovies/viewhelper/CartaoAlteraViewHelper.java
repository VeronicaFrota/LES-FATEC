package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.equipemovies.ecommercemovies.domain.Cartao;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;

public class CartaoAlteraViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Cartao cartao = new Cartao();
		
		if (request.getParameter("operacao") != null
		           && request.getParameter("operacao").equals("alterar")
		           && request.getParameter("id_cartao") != null ) {
			
			String idBanderira = request.getParameter("id_bandeira");
			String numeroCartao = request.getParameter("numero_cartao");
			String cvv = request.getParameter("cvv");
			String dataValidade = request.getParameter("data_validade");
			
			String id = request.getParameter("id_cartao");
			
			cartao.setId(Integer.valueOf(id));
			cartao.setIdBandeira(Integer.valueOf(idBanderira));
			cartao.setNumeroCartao(numeroCartao);
			cartao.setCvv(cvv);
			cartao.setDataValidade(dataValidade);
		}
		
		return cartao;
	}

	@Override
	public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {
		if (result.getMsg() != null) {
			return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
		}
		return null;
	}

}
