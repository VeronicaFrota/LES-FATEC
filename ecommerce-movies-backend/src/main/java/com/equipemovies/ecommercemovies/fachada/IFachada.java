package com.equipemovies.ecommercemovies.fachada;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.Resultado;

public interface IFachada {
    Resultado salvar(EntidadeDominio entidade);
    Resultado alterar(EntidadeDominio entidade);
    Resultado excluir(EntidadeDominio entidade);
    Resultado consultar(EntidadeDominio entidade);
}
