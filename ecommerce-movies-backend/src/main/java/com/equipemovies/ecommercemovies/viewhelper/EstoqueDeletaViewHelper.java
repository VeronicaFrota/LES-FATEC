package com.equipemovies.ecommercemovies.viewhelper;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Estoque;
import com.equipemovies.ecommercemovies.util.Resultado;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EstoqueDeletaViewHelper implements IViewHelper {

    //
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        if(request.getParameter("operacao") != null
                && request.getParameter("operacao").toLowerCase().equals("excluir")
                && request.getParameter("id_estoque") != null) {

            Estoque estoque = new Estoque();

            //Pega o id do estoque
            String id = request.getParameter("id_estoque");
            estoque.setId(Integer.parseInt(id));

            estoque.setStatus(false);
            System.out.println("id = " + id);
            return estoque;

        }

        return null;
    }

    @Override
    public ResponseEntity getView(Resultado result,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {

        return null;
    }
}
