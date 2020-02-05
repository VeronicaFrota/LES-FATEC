package com.equipemovies.ecommercemovies.viewhelper;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Estoque;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EstoqueAlteraViewHelper implements IViewHelper {
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        Estoque estoque = new Estoque();

        //Verifica se a operação é de alterar
        if (request.getParameter("operacao") != null
                && request.getParameter("operacao").equals("alterar")
                && request.getParameter("id_estoque") != null ) {


            //Informações de estoque
            String fornecedor = request.getParameter("fornecedor");
            String quantidadeEstoque = request.getParameter("quantidadeEstoque");
            String valorCompra = request.getParameter("valorCompra");
            String valorVenda = request.getParameter("valorVenda");

            //Insere informações
            String id = request.getParameter("id_estoque");
            estoque.setId(Integer.parseInt(id));

            estoque.setFornecedor(fornecedor);
            estoque.setQuantidadeEstoque(Integer.parseInt(quantidadeEstoque));
            estoque.setValorCompra(Float.parseFloat(valorCompra));
            estoque.setValorVenda(Float.parseFloat(valorVenda));

        }

        return estoque;
    }

    // Pega o resultado.
    @Override
    public ResponseEntity getView(Resultado result,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {

        if (result.getMsg() != null) {
            return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
        }

        return null;
    }


}
