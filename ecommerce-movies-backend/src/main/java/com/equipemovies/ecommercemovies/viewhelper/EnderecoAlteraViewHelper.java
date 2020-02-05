package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.equipemovies.ecommercemovies.domain.Endereco;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;

public class EnderecoAlteraViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Endereco endereco = new Endereco();
		
		//Verifica se a operação é de alterar
		if (request.getParameter("operacao") != null
		           && request.getParameter("operacao").equals("alterar")
		           && request.getParameter("id_endereco") != null ) {
			
			//Informações de endereço
			String idCidade = request.getParameter("id_cidade");
			String logradouro = request.getParameter("logradouro");
			String numero = request.getParameter("numero");
			String bairro = request.getParameter("bairro");
			String cep = request.getParameter("cep");
			String observacao = request.getParameter("observacao");
			
			//Insere informações 
			String id = request.getParameter("id_endereco");
			endereco.setId(Integer.parseInt(id));
			
			endereco.setIdCidade(Integer.parseInt(idCidade));
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setCep(cep);
			endereco.setObservacao(observacao);
			
		}
		
		return endereco;
	}


	@Override
	public ResponseEntity getView(Resultado result,
								  HttpServletRequest request,
								  HttpServletResponse response) {
		if (result.getMsg() != null) {
			return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
		}

		return null;
	}

}
