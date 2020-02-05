package com.equipemovies.ecommercemovies.viewhelper;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Pedido;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PedidoEspecificoViewHelper implements IViewHelper {
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        Pedido pedido = new Pedido();

        if (request.getParameter("operacao") != null
                && request.getParameter("operacao").equals("consultar")
                && request.getParameter("id_cliente") != null ) {

            String idCliente = request.getParameter("id_cliente");

            pedido.setIdCliente(Integer.parseInt(idCliente));

        }
        return pedido;
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
