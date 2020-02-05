package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.util.Resultado;

import org.springframework.http.ResponseEntity;

public interface IViewHelper {

    EntidadeDominio getEntidade(HttpServletRequest request);

    ResponseEntity getView(Resultado result,
                           HttpServletRequest request,
                           HttpServletResponse response);
}
