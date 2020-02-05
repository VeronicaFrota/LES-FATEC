package com.equipemovies.ecommercemovies.negocio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.equipemovies.ecommercemovies.domain.Cartao;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;

public class ValidadorDadosCartao implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		String erros = "";
		
		if (entidade instanceof Cartao) {
			Cartao cartao = (Cartao) entidade;
			
			Integer bandeira = cartao.getIdBandeira();
			String numeroCartao = cartao.getNumeroCartao();
			String cvv = cartao.getCvv();
			String dataValidade = cartao.getDataValidade();
			
			//1ª Regra de Negócio - Campos nulos
			if(bandeira == null || numeroCartao == null || cvv == null ||
					dataValidade == null) {
				erros += "Todos os campos devem ser preenchidos!";
			}
			
			//2ª Regra de negócio - Tamanho CVV
			if (cvv.length() != 3) {
				System.out.println("Erro CVV");
				erros += "|| O CVV informado não está correto";
			}
			
			//3ª Regra de negócio - Tamanho Numero do Cartao
			if (numeroCartao.length() != 16) {
				erros += "|| O número de cartão informado não está correto";
			}
			
			//4ª Regra de negócio - Validação do layout da Data de validade
			String regexDataValidade = "^\\d{2}/\\d{4}$";
			
			Pattern patternDataValidade = Pattern.compile(regexDataValidade);
			Matcher matcherDataValidade = patternDataValidade.matcher(dataValidade);
			
			if(!matcherDataValidade.matches()) {
				erros += "|| A data de validade informada é inválida! Ela deve seguir o padrão MM-YYYY";
			}
			else {
				//5ª Regra de negócio - Validação da data de validade
				String mes = dataValidade.substring(0, 2);
				String ano = dataValidade.substring(3, 7);
				int anoData = Integer.parseInt(ano);
				int mesData = Integer.parseInt(mes);
				if(anoData < 2019) {
					erros += "|| O ano da data de validade do cartão não é válido";
				}
				if(mesData <= 0 || mesData > 12){
					erros += "|| Deve ser inserida um mês de nascimento válida";
				}
			}
		}
		return erros.equals("") ? null : erros;
	}

}
