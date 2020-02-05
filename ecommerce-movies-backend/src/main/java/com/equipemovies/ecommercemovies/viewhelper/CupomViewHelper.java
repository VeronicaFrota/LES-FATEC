package com.equipemovies.ecommercemovies.viewhelper;

import com.equipemovies.ecommercemovies.domain.Cupom;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CupomViewHelper implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        Cupom cupom = new Cupom();

        if(request.getParameter("operacao") != null
                && request.getParameter("operacao").toLowerCase().equals("salvar")){
        	
        	System.out.println("Entrou ViewHelper Salvar");

        	String idCliente = request.getParameter("idCliente");
            String codigo = request.getParameter("codigo");
            String valor = request.getParameter("valor");

            if(codigo != null && !codigo.trim().equals("")) {
                cupom.setCodigo(codigo);
            }

            if(valor != null) {
                cupom.setValor(Float.parseFloat(valor));
            }
            
            if(idCliente != null) {
            	cupom.setIdCliente(Integer.parseInt(idCliente));
            }
            
        }

		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("consultar")) {
			
			System.out.println("Entrou na ViewHelper Consulta");
			
			String idCliente = request.getParameter("idCliente");
			
			if(!StringUtils.isEmpty(idCliente)) {
				cupom.setIdCliente(Integer.parseInt(idCliente));
			}
			if (request.getParameter("status") != null) {
				cupom.setStatus(request.getParameter("status").equalsIgnoreCase("1"));
			}
		}
		
		System.out.println(cupom);
        
        return cupom;
    }

    @Override
    public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {
    	if (result.getMsg() != null) {
			return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
		}

		return ResponseEntity.ok(result.getEntidadeDominio());
    }
}
