package com.equipemovies.ecommercemovies.negocio;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Filme;

public class ValidadorDadosFilme implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {

		// para pegar os erros dos campos e exibir m uma mensagem no form(para fazer a concatenação nos if's aqui em baixo)
		String erros = "";
		
		if(entidade instanceof Filme) {
			Filme filme = (Filme)entidade;

			if (filme.getId() != null && filme.getStatus() != null) {
				return null;
			}

			String nome = filme.getNome();
        	String codigoBarras = filme.getCodigoBarras(); 
        	int idGenero = filme.getGenero().getId();
        	int idPaisOrigem = filme.getPaisOrigem().getId();
        	int idIdioma = filme.getIdioma().getId();
        	int idClassificacaoEtaria = filme.getClassificacaoEtaria().getId();
        	int ano = filme.getAno();
        	String sinopse = filme.getSinopse();
			
			// 1º regra
			if (nome == null || codigoBarras == null|| idGenero == 0 ||
					idPaisOrigem == 0 || idIdioma == 0 || idClassificacaoEtaria == 0 || ano < 0
					|| sinopse == null){
				erros += "Todos os campos são de preenchimento obrigatório!\n";
			} else {
				if (nome.trim().equals("")){
					erros += "Nome, codigo e categoria são de preenchimento obrigatório!";
				}
			}

			// 2º regra
			if (ano <= 1990 || ano > 2019) {
				erros += "Deve ser inserido um ano válido!";
			}

			// 3º regra
			if (sinopse != null && sinopse.length() < 10) {
				erros += "A sinopse deve conter mais do que 10 (dez) caracteres";
			}

			// 4º regra
			if (codigoBarras != null && codigoBarras.length() > 9) {
				erros += "O código de barras deve conter até 9 (nove) caracteres";
			}

			// 5º regra
			if (nome != null && nome.length() > 50) {
				erros += "O nome deve conter até 50 (cinquenta) caracteres";
			}

		} else {
			
			erros += "Deve ser realizado o registro de um filme!";
		}
		
		return erros.equals("") ? null : erros;

	}
}

