package com.equipemovies.ecommercemovies.negocio;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.Resultado;

public interface IStrategyRegraNegocio {

	public Resultado processar(EntidadeDominio entidade, Resultado resultado);

}
