package com.equipemovies.ecommercemovies.negocio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.equipemovies.ecommercemovies.domain.Endereco;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;

public class ValidadorDadosEndereco implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		String erros = "";
		
		if(entidade instanceof Endereco) {
			Endereco endereco = (Endereco) entidade;
			
			Integer cidade = endereco.getIdCidade();
			String logradouro = endereco.getLogradouro();
			String numero = endereco.getNumero();
			String bairro = endereco.getBairro();
			String cep = endereco.getCep();
			String observacao = endereco.getObservacao();
			
			//1ª Regra de negócio - Campos nulos
			if (cidade == null || logradouro == null || numero == null ||
					bairro == null || cep == null || observacao == null) {
				erros += "Todos os campos devem ser preenchidos - ";
			}
			
			//2ª Regra de negócio - Validação do CEP
			String regexCep = "^\\d{5}-\\d{3}$";
			
			Pattern patternCep = Pattern.compile(regexCep);
			Matcher matcherCep = patternCep.matcher(cep);
			
			if (!matcherCep.matches()) {
				erros += "|| O CEP informado não é válido! Ele deve seguir o layout XXXXX-XXX";
			}
			
			//3ª Regra de negócio - Validação Observacao
			if (observacao.length() < 20) {
				erros += "|| A observação deve conter pelo menos 20 caracteres";
			}
			
		}
		
		return erros.equals("") ? null : erros;
	}

}
