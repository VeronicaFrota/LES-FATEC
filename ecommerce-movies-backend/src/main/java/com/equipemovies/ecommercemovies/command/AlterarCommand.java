package com.equipemovies.ecommercemovies.command;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.Resultado;

public class AlterarCommand extends AbstractCommand{

	public Resultado execute(EntidadeDominio entidade) {
		return fachada.alterar(entidade);
	}
	
}
