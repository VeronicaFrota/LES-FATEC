package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.equipemovies.ecommercemovies.domain.Endereco;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Usuario;
import com.equipemovies.ecommercemovies.util.Criptografia;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

public class LoginViewHelper implements IViewHelper {

	//Pega a entidade
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		//Instancia a entidade usuario
		Usuario usuario = new Usuario();
		
		//Verifica se a opereção corresponde as informações de de get
		if(request.getParameter("operacao") != null 
        		&& request.getParameter("operacao").toLowerCase().equals("login")) {

			//Informações Cliente

			String email = request.getParameter("email");
			String senha = request.getParameter("senha");

			//Validação email
			if (email != null && !email.trim().equals("")) {
				usuario.setEmail(email);
			}

			//Validação senha
			if (senha != null && !senha.trim().equals("")) {
				usuario.setSenha(Criptografia.encriptografar(senha));
			}

		}
		return usuario;
	}

	//Pega o retorno
	@Override
	public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {
		
		if (result.getMsg() != null) {
			return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
		}

		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("login")) {

			if (CollectionUtils.isEmpty(result.getEntidadeDominio())) {
				return ResponseEntity.badRequest().build();
			}
			
			return ResponseEntity.ok(result.getEntidadeDominio().get(0));
		}

		return ResponseEntity.ok(result.getEntidadeDominio());
	}

}
