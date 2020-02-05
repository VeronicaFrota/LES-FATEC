package com.equipemovies.ecommercemovies.viewhelper;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Filme;
import com.equipemovies.ecommercemovies.domain.Genero;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoughnutViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        Genero genero = new Genero();

        // Para realizar a consulta de um filme(id) expecifico
        if(request.getParameter("operacao") != null
                && request.getParameter("operacao").toLowerCase().equals("consultar")
                && request.getParameter("id_genero") != null) {

            String id = request.getParameter("id_genero");
            genero.setId(Integer.parseInt(id));
        }


        return genero;
    }


    @Override
    public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {

        if (result.getMsg() != null) {
            return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
        }

        if(request.getParameter("operacao") != null
                && request.getParameter("operacao").toLowerCase().equals("consultar")
                && request.getParameter("id_genero") != null) {

            if (CollectionUtils.isEmpty(result.getEntidadeDominio())) {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok(result.getEntidadeDominio().get(0));
        }

        return ResponseEntity.ok(result.getEntidadeDominio());
    }
}
