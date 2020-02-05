package com.equipemovies.ecommercemovies.command;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.Resultado;

/*
 *  Command encapsular uma solicitação como um objeto, o que lhe permite parametrizar outros objetos com diferentes
 *   solicitações, enfileirar ou registrar solicitações e implementar recursos de cancelamento de operações.
 */
public interface ICommand {
	
	// executa a classe resultado
    Resultado execute(EntidadeDominio entidade);
}
