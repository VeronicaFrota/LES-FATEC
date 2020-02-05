package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.SenhaUsuario;
import com.equipemovies.ecommercemovies.domain.Usuario;
import com.equipemovies.ecommercemovies.util.Criptografia;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;

public class UsuarioSenhaViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		SenhaUsuario usuario = new SenhaUsuario();
		
		if (request.getParameter("operacao") != null
		           && request.getParameter("operacao").equals("consultar")
		           && request.getParameter("id_cliente") != null 
		           && request.getParameter("senha") != null) {
			
			String id = request.getParameter("id_cliente");
			String senha = request.getParameter("senha");
			usuario.setId(Integer.parseInt(id));
			usuario.setSenha(Criptografia.encriptografar(senha));
		} else if (request.getParameter("operacao") != null
		           && request.getParameter("operacao").equals("alterar")
		           && request.getParameter("id_cliente") != null 
		           && request.getParameter("senha") != null) {
			
			String id = request.getParameter("id_cliente");
			String senha = request.getParameter("senha");
			usuario.setId(Integer.parseInt(id));
			usuario.setSenha(Criptografia.encriptografar(senha));
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
                && request.getParameter("id_cliente") != null 
		        && request.getParameter("senha") != null) {

            if (CollectionUtils.isEmpty(result.getEntidadeDominio())) {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok(result.getEntidadeDominio().get(0));
        }

        return ResponseEntity.ok(result.getEntidadeDominio());
    }
}
