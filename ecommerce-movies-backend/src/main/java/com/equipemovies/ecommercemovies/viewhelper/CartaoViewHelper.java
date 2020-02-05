package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.equipemovies.ecommercemovies.domain.Cartao;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;

public class CartaoViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Cartao cartao = new Cartao();
		
		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("salvar")){
			
			String idCliente = request.getParameter("idCliente");
			String idBandeira = request.getParameter("idBandeira");
			String numeroCartao = request.getParameter("numeroCartao");
			String cvv = request.getParameter("cvv");
			String dataValidade = request.getParameter("dataValidade");
			
			cartao.setIdCliente(Integer.valueOf(idCliente));
			
			if(idBandeira != null) {
				cartao.setIdBandeira(Integer.valueOf(idBandeira));
			}
			
			if(numeroCartao != null && !numeroCartao.trim().equals("")) {
				cartao.setNumeroCartao(numeroCartao);
			}
			
			if(cvv != null) {
				cartao.setCvv(cvv);
			}
			
			if(dataValidade != null && !dataValidade.trim().equals("")) {
				cartao.setDataValidade(dataValidade);
			}
			
		}
		
		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("consultar")) {
				
				String idCartao = request.getParameter("id_cartao");
				String idCliente = request.getParameter("id_cliente");
				
				if(!StringUtils.isEmpty(idCartao))
				{
					cartao.setId(Integer.parseInt(idCartao));
				}
				
				if(!StringUtils.isEmpty(idCliente))
				{
					cartao.setIdCliente(Integer.parseInt(idCliente));
				}
				
			}
		
		return cartao;
	}

	@Override
	public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {
		if (result.getMsg() != null) {
			return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
		}

		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("consultar")
				&& request.getParameter("id_cartao") != null) {

			if (CollectionUtils.isEmpty(result.getEntidadeDominio())) {
				return ResponseEntity.badRequest().build();
			}
			
			return ResponseEntity.ok(result.getEntidadeDominio().get(0));
		}
		return ResponseEntity.ok(result.getEntidadeDominio());
	}

}
