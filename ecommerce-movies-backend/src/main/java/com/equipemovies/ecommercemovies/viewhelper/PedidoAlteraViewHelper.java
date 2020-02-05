package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Pedido;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;

public class PedidoAlteraViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Pedido pedido = new Pedido();
		
		if (request.getParameter("operacao") != null
		           && request.getParameter("operacao").equals("alterar")
		           && request.getParameter("id_pedido") != null ) {
			
			String id = request.getParameter("id_pedido");
			String idTransacao = request.getParameter("id_transacao");
			
			pedido.setIdTransacao(Integer.parseInt(idTransacao));
			pedido.setId(Integer.parseInt(id));

		}
		return pedido;
	}

	@Override
	public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {

		if (result.getMsg() != null) {
			return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
		}
		
		return null;
	}

}
