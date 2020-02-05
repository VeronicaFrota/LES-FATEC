package com.equipemovies.ecommercemovies.viewhelper;

import com.equipemovies.ecommercemovies.domain.Cupom;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Pedido;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CupomEspecificoViewHelper implements IViewHelper {
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        Cupom cupom = new Cupom();

        if (request.getParameter("operacao") != null
                && request.getParameter("operacao").equals("consultar")
                && request.getParameter("id_cliente") != null ) {

            String idCliente = request.getParameter("id_cliente");

            cupom.setIdCliente(Integer.parseInt(idCliente));

        }
        return cupom;
    }

    @Override
    public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {
        if (result.getMsg() != null) {
            return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
        }


        if(request.getParameter("operacao") != null
                && request.getParameter("operacao").equals("consultar")
                && request.getParameter("id_cliente") != null) {

            if (CollectionUtils.isEmpty(result.getEntidadeDominio())) {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok(result.getEntidadeDominio());
        }

        return null;
    }
}
