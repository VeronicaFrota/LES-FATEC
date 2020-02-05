package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import com.equipemovies.ecommercemovies.domain.EmailUsuario;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Usuario;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;

public class UsuarioEmailViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		EmailUsuario usuario = new EmailUsuario();
		
		if (request.getParameter("operacao") != null
		           && request.getParameter("operacao").equals("consultar")
		           && request.getParameter("email") != null) {
			
			String email = request.getParameter("email");
			System.out.println(email);
			
			usuario.setEmail(email);
		}
		
		return usuario;
	}

	@Override
	public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {
		if (result.getMsg() != null) {
            return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
        }

        if(request.getParameter("operacao") != null
                && request.getParameter("operacao").toLowerCase().equals("consultar")
                && request.getParameter("email") != null) {

            if (CollectionUtils.isEmpty(result.getEntidadeDominio())) {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok(result.getEntidadeDominio().get(0));
        }

        return ResponseEntity.ok(result.getEntidadeDominio());
    }
}
