package com.equipemovies.ecommercemovies.viewhelper;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Filme;
import com.equipemovies.ecommercemovies.util.Resultado;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieAtivaViewHelper implements IViewHelper {
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        if(request.getParameter("operacao") != null
                && request.getParameter("operacao").toLowerCase().equals("alterar")
                && request.getParameter("id_filme") != null) {

            Filme filme = new Filme();

            // para pegar o id do filme
            String id = request.getParameter("id_filme");
            filme.setId(Integer.parseInt(id));

            filme.setStatus(true);

            return filme;
        }

        return null;
    }

    @Override
    public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        return null;
    }

}

