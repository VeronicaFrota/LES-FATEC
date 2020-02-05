package com.equipemovies.ecommercemovies.viewhelper;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.equipemovies.ecommercemovies.domain.Endereco;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;

public class EnderecoViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		//Instancia a entidade endereco
		Endereco endereco = new Endereco();
		
		//Verifica se a operação é do tipo salvar
		if(request.getParameter("operacao") != null 
        		&& request.getParameter("operacao").toLowerCase().equals("salvar")) {
			
			//Informações de endereço
			String idCliente = request.getParameter("idCliente");	//Após sera substituido pelo que for pego da sessão
			String idCidade = request.getParameter("idCidade");
			String logradouro = request.getParameter("logradouro");
			String numero = request.getParameter("numero");
			String bairro = request.getParameter("bairro");
			String cep = request.getParameter("cep");
			String observacao = request.getParameter("observacao");
			
			//Validações de endereco
			
			//Sera trocado pelo id do cliente da sessão
			endereco.setIdCliente(Integer.valueOf(idCliente));

			//Valida o id da Cidade
			if(idCidade != null) {
				endereco.setIdCidade(Integer.valueOf(idCidade));
			}
			
			//Valida o logradouro
			if (logradouro != null && !logradouro.trim().equals("")) {
				endereco.setLogradouro(logradouro);
			}
			
			//Valida o numero
			if (numero != null && !numero.trim().equals("")) {
				endereco.setNumero(numero);
			}
			
			//Valida o bairro
			if (bairro != null && !bairro.trim().equals("")) {
				endereco.setBairro(bairro);
			}
			
			//Valida o cep
			if (cep != null && !cep.trim().equals("")) {
				endereco.setCep(cep);
			}
			
			//Valida a observação
			if (observacao != null && !observacao.trim().equals("")) {
				endereco.setObservacao(observacao);
			}
			
			//Insere data de cadastro do endereco
			endereco.setDataCadastro(new Date());
			
			
		}
		
		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("consultar")) {
			
			String idEndereco = request.getParameter("id_endereco");
			String idCliente = request.getParameter("id_cliente");
			
			if(!StringUtils.isEmpty(idEndereco)) {
				endereco.setId(Integer.parseInt(idEndereco));
			}
			
			if(!StringUtils.isEmpty(idCliente)) {
				endereco.setIdCliente(Integer.parseInt(idCliente));
			}
			
		}
		
		System.out.println(endereco.getId());
		
		return endereco;
		
		
	}

	@Override
	public ResponseEntity getView(Resultado result,
								  HttpServletRequest request,
								  HttpServletResponse response) {
		
		if (result.getMsg() != null) {
			return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
		}

		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("consultar")
				&& request.getParameter("id_endereco") != null) {

			if (CollectionUtils.isEmpty(result.getEntidadeDominio())) {
				return ResponseEntity.badRequest().build();
			}
			
			return ResponseEntity.ok(result.getEntidadeDominio().get(0));
		}

		return ResponseEntity.ok(result.getEntidadeDominio());
	}

}
