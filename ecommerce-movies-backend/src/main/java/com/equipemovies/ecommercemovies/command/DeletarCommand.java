package com.equipemovies.ecommercemovies.command;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.Resultado;

public class DeletarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.excluir(entidade);
	}


}
