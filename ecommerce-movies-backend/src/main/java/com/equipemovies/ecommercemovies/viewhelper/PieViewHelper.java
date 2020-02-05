package com.equipemovies.ecommercemovies.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.GeneroPie;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;

public class PieViewHelper implements IViewHelper{
	
	@Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        GeneroPie genero = new GeneroPie();

        // Para realizar a consulta de um filme(id) expecifico
        if(request.getParameter("operacao") != null
                && request.getParameter("operacao").toLowerCase().equals("consultar")
                && request.getParameter("mes") != null) {

            String mes = request.getParameter("mes");
            genero.setMes(Integer.parseInt(mes));
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
